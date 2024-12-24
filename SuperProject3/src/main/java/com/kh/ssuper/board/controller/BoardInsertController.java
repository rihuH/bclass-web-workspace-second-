package com.kh.ssuper.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.ssuper.board.model.service.BoardService;
import com.kh.ssuper.board.model.vo.Attachment;
import com.kh.ssuper.board.model.vo.Board;
import com.kh.ssuper.common.MyRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/insert.board")
public class BoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardInsertController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1) 인코딩 설정(UTF-8)
		request.setCharacterEncoding("UTF-8");
		// 2) 값 뽑기
//		String userNo = request.getParameter("userNo");
//		System.out.println(userNo); // null
		//form 태그 요청 시 multipart/form-data 형식으로 전송하는 경우 request.getParameter로 값 뽑기가 불가능.
		
		// 라이브러리가 추가로 필요
		// com.oreilly.servlet (앞글자만 따서 cos라고 한다) - 파일 다운받아서 WEB-INF의 lib에 넣기		
		// 스탭0) 요청이 multipart방식으로 잘 전송이 되었는지부터 확인
		if(ServletFileUpload.isMultipartContent(request)) {
//			System.out.println("요청 잘 옴~~");
			
			// 스탭 1) 전송되는 파일의 처리를 위한 작업
			// 1_1. 전송파일 용량 제한
			
			/*
			 * 단위 정리
			 * bit (1, 0 들어가는 한 칸)
			 * 8 bit = 1 Byte/ * 1024 = 1 kByte / * 1024 = MByte / *1024 = Tbyte / *1024 = PByte...
			 * 
			 * 10MegaByte
			 */
			
			int maxSize = 1024*1024*10; // byte단위로 써야함. 10Mbyte로 제한을 두고 싶음.
			
			// 1_2. 전달된 파일을 저장할 서버의 폴더 경로 알아내기.
			// 파일은 resources의 board_upfiles에, DB에는 그 경로와 파일명을 저장할 예정
			// getRealPath()
			// HttpServletRequest 는 응답 후 사라지는 애
			// HttpSession 서블릿, jsp/ 클래스에서는 못 씀.
			// ServletContext를 써서 => 인자값으로 webapp부터 board_upfiles폴더까지의 경로를 문자열로 전달
			HttpSession session = request.getSession();
			ServletContext application = session.getServletContext(); // request도 메소드 있지만 순차적으로 접근하라고 권장.
			String savePath = application.getRealPath("/resources/board_upfiles");
			
			// 스텝 2) 서버에 업로드
			
			// a.jpg  똑같은 파일 올라오면 덮어쓰기 되니까 넘버링해줌 a2.jpg a3.jpg.....
			
			/*
			 * - HttpServletRequest request
			 * => MultipartRequest multiRequest객체로 변환
			 * 
			 * [ 표현법 ] 
			 * MultiPartRequest multiRequest = new MultiPartRequest(request, savePath, maxSize, 인코딩방식, 파일명을 수정해주는 객체);
			 * 위 객체 생성 시점에 파일이 업로드 됨.
			 * MultiPartRequest객체를 생성하면 파일이 업로드!
			 * 
			 * 사용자가 올린 파일명은 해당 폴더에 업로드하지 않는 것이 일반적!
			 * Q) 파일명을 수정하는 이유는?
			 * A) 같은 파일명이 존재할 수 있으니까.
			 * 	  파일명에 한글/ 특수문자/ 공백문자 포함되어있을 수도 있음. 개발 시 피해야하는 문자. 서버에 따라 문제가 일어날 수 있음.
			 * 
			 * cor.jar => 기본적으로 파일명을 수정해주는 객체 존재함. 
			 * => 파일을 업로드할 때 내부적으로 rename()메소드 호출하면서 파일명 수정
			 * => bono1.jpg bono2.jpg 이런 식으로 바꿔줌.
			 * =>네이밍이 별로라서 파일명을 수정해주는 새 클래스를 작성.
			 * MyRenamePolicy
			 * 
			 */
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyRenamePolicy());
			// 값을 '이관' 했다고 표현함. 이제 값을 뽑을 수 있음
			// 여기까지 --- 파일 업로드 ---

			
			//이제 BOARD 테이블에 INSERT!
			String userNo = multiRequest.getParameter("userNo");
			String title = multiRequest.getParameter("title");
			String content = multiRequest.getParameter("content");
			String categoryNo = multiRequest.getParameter("category");
			
			// 3) VO객체로 가공 => BOARD테이블에 INSERT할 내용
			Board board = new Board();
			board.setBoardTitle(title);
			board.setBoardContent(content);
			board.setBoardWriter(userNo);
			board.setCategory(categoryNo);
			
			// has one has many, one to one one to many
			// 테이블 설계
			// 한 게시글은 한 행으로 들어가는데 예를 들어 여러 파일이 올라갈 수 있어 1대 다수 관계가 된다면 파일은 테이블이 따로 빠져야한다.
			// 예컨대 지금 attachment 테이블은, 
			// 처음은 식별용도의 파일넘버 PK, / 참조하는 게시글 FK/ 파일의 원본명 / 바뀐 파일명/ 일반게시글 저장 파일과 사진게시글 보관파일 저장경로를 구분해놨기 때문에 저장경로도/
			// 업로드 날짜/ 파일이 여러개가 올라갈 경우 하나는 메인에 띄워지고 나머지는 상세에 출력될 텐데, 썸네일이냐 아니냐(1/2)를 구분하르 용도/ 삭제여부
			
			// 3_2) 첨부파일의 경우 => 선택적
			Attachment attachment = null;
			System.out.println(savePath);
			//C:\bclass99\web-workspace2\SuperProject3\src\main\webapp\resources\board_upfiles
			
			// 첨부파일의 유무를 파악
			// multiRequest.getOriginalFileName("키값") - input type="file"인 태그의 name속성값이 키값.
			// 첨부파일이 존재하면 "원본파일명" // 존재하지 않는다면 null값을 반환.
			if(multiRequest.getOriginalFileName("upfile") != null) {
				// 첨부파일 있다 !! => VO객체로 가공
				attachment = new Attachment();
				// originName
				attachment.setOriginName(multiRequest.getOriginalFileName("upfile"));
				
				// changeName
				attachment.setChangeName(multiRequest.getFilesystemName("upfile")); // 이건 바뀐 이름
				
				//filePath
				attachment.setFilePath("resources/board_upfiles"); // savePath랑은 다름
			}
			
			// 4) 서비스 호출
			int result = new BoardService().insert(board, attachment);
			// board랑 attachment랑 같이 가야 하는 이유 -> insert 하다가 문제가 생길 수가 있으므로**********
			// 문제가 생기면 둘 다 등록이 안 되고 안 생기면 둘 다 등록되어야 한다.***********
			// 둘을 하나의 transaction으로 묶어야 한다.***********
			// 그래서 메소드가 따로 가면 안 됨.
			// transaction 생각 중요**********
			
			// 5) 응답화면 지정
			if(result > 0) { // 성공
				request.getSession().setAttribute("alertMsg", "게시글등록 성공~");
				
//				request.getRequestDispatcher("\\webapp\\WEB-INF\\views\\board\\board_list.jsp").forward(request, response);
				// 포워딩 하면 안됨, 지금 request에는 게시글 정보가 하나도 안 담겨 있음.*******
				
				response.sendRedirect(request.getContextPath() + "/list.board?currentPage=1"); 
				// list.board까지만 쓰면, currentPage가 없어서 nullpointException 발생
				// 뒤에 값을 붙여서 보내줘야한다.
				
			} else { // 실패
				// 테이블 인서트와 별개로 파일 업로드는 멀티파트 객체가 생성되면 저장되는 것이다.
				// 게시글 인서트 실패하면 파일도 저장할 필요가 없으므로 저장된 파일도 지워주는 것이 좋다.
				if(attachment != null) {
					new File(savePath + "/" + attachment.getChangeName()).delete();// 파일 지워주기
				}
				
				request.setAttribute("failMsg", "게시글 작성 실패");
				request.getRequestDispatcher("WEB-INF/views/common/fail_page.jsp").forward(request, response);
			}
			
			
			
			
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
