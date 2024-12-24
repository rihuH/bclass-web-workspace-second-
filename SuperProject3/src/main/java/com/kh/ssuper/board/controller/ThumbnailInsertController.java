package com.kh.ssuper.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.ssuper.board.model.service.BoardService;
import com.kh.ssuper.board.model.vo.Attachment;
import com.kh.ssuper.board.model.vo.Board;
import com.kh.ssuper.common.MyRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/save.thumbnail")
public class ThumbnailInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ThumbnailInsertController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 2) 첨부파일 -> multipart/form-date => 조건제시 => 서버로 파일을 올려주자
		if(ServletFileUpload.isMultipartContent(request)) {
			// 1) MultipartRequest객체 생성
			// 1_1. 전송용량제한(10Byte)
			int maxSize = 1024*1024*100; // 100MByte
			
			
			
			// 1_2. 저장할 경로를 구해야함!
			String savePath = request.getSession().getServletContext().getRealPath("/resources/image_upfiles");
			// getRealPath : 논리적 경로를 가지고 물리적인 경로를 얻어냄
			// 물리적 경로 ㅣ C:\bclass99\web-workspace2\SuperProject3\src\main\webapp\resources\image.upfiles
			// 위 값을 얻어내기 위해서 "/resources/image.upfiles" 라는 String 문자열값 전달(논리적 경로)
			
			// 2) MultipartRequest객체를 생성하면서 파일의 이름을 바꿔주면서 업로드!!!
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyRenamePolicy());
			//////////////////멀티파트 객체 생성되는 순간 파일 업로드. 내 컴퓨터의 물리적인 저장장치에 저장됨.
			//--------------------------------------------------------여기까지 파일업로드
//			System.out.println(request.getSession().getAttribute("loginUser")); ******************* 이렇게 세션에 있는 값을 뽑을 수 있음
			
			// 3) multiRequest로부터 값 뽑기 => getParameter() 호출
			String boardTitle = multiRequest.getParameter("title");
			String boardContent = multiRequest.getParameter("content");
			String userNo = multiRequest.getParameter("userNo");
			
			// 4) 가공
			Board board = new Board();
			board.setBoardTitle(boardContent);
			board.setBoardContent(boardContent);
			board.setBoardWriter(userNo);
			
			// Attachment
			// => 사진게시판 작성 폼 required
			// => 게시글 한 개 당 최소 한 개의 첨부파일은 존재
			
			// file1 -> 무조건 있음, file2, file3, file4
			// 여러 개의 VO를 묶어서 다룰 경우 List
			List<Attachment> list = new ArrayList();
			
			for(int i = 1; i <= 4; i++) {
				// 조건검사. name속성값을 이용해서 파일이 첨부되었나
				// 현재 반복하고 있는 키값으로 파일이 업로드되었는지 파악!
				String key = "file" + i;
				if(multiRequest.getOriginalFileName(key) != null) {
					// 파일이 존재한다.
					Attachment at = new Attachment();
					at.setOriginName(multiRequest.getOriginalFileName(key));
					at.setChangeName(multiRequest.getFilesystemName(key));
					at.setFilePath("resources/image_upfiles");
					
					// 파일레벨
					at.setFileLevel(i == 1 ? 1 : 2);
					
					list.add(at);
				}
			}
			
			// 5) 서비스 요청
			int result = new BoardService().insertThumbnailBoard(board, list);
			
			if(result > 0) {
				request.getSession().setAttribute("alertMsg", "성공");
				response.sendRedirect(request.getContextPath() + "/list.thumbnail");
			} else {
				request.getSession().setAttribute("failMsg", "실패");
				request.getRequestDispatcher("/WEB-INF/views/common/fail_page.jsp").forward(request, response);
			}
			
			
			
			
			
			
			
			
			
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
