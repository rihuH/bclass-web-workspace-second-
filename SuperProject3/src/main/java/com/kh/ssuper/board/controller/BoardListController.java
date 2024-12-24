package com.kh.ssuper.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.ssuper.board.model.service.BoardService;
import com.kh.ssuper.board.model.vo.Board;
import com.kh.ssuper.common.PageInfo;

@WebServlet("/list.board")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardListController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// - 페이징 처리 - 
		// 필요한 변수들
		int listCount; // 현재 일반게시판의 총 게시글의 개수
		// => BOARD테이블 가서 개수 세어야 함 COUTN(*) (STATUS = 'Y')해서 조회
		int currentPage; // 현재 사용자가 요청한 페이지 
		//=> request.getParameter("currentPage")로 뽑아서 사용
		int pageLimit; // 페이지 하단에 보여줄 페이징버튼 개수 => 10개로 고정
		int boardLimit; // 한 페이지에 보여질 게시글의 최대 개수 => 10개로 고정
		
		int maxPage; // 가장 마지막 페이지가 몇 번 페이지인지(총 페이지의 개수)
		int startPage; // 페이지 하단에 보여질 페이징바의 시작 수
		int endPage; // 페이지 하단에 보여질 페이징바의 끝 수 
		// * listCount : 총 게시글의 수
 		listCount = new BoardService().selectListCount();
 		
		//* currentPage : 현재페이지(사용자가 요청한 페이지)
 		currentPage = Integer.parseInt(request.getParameter("currentPage"));
 		
 		//* pageLimit : 페이징버튼 최대 개수
 		pageLimit = 10;
 		
 		// * boardLimit : 한 페이지에 보여질 게시글의 최대 개수
 		boardLimit = 10;
 		
 		// * maxPage : 가장 마지막페이지가 몇 번 페이지인지(총 페이지의 개수)
 		/*
 		 * listCount, boardLimit 에 영향을 받음
 		 * - 공식 구하기
 		 *   단, boardLimit이 10이라고 가정
 		 *   
 		 * 총 개수 / 한 페이지 개수 = 마지막 페이지
 		 * 
 		 * 스텝.
 		 * 1. listCount를 double로 변환
 		 * 2. listCount / boardLimit
 		 * 3. Math.ceil() => 결과를 올림처리
 		 * 4. 결과값을 int로 형변환
 		 * 
 		 */
// 		System.out.println(pageLimit/boardLimit + Math.ceil(1/(pageLimit%boardLimit))); // 어차피 ceil메소드를 쓸 거라면 이게 더 비효율적
// 		System.out.println(((double)pageLimit/(double)boardLimit));
// 		System.out.println(((double)pageLimit/boardLimit)); 다른 타입끼리 연산하면 큰 쪽으로 형변환 자동 되니까 둘 다 안 써줘도 됨
 		
 		maxPage = (int)Math.ceil((double)listCount/boardLimit);
 		
 		// * startPage : 페이지 하단에 보여질 페이징 버튼 중 시작
 		
 		/*
 		 * pageLimit, currentPage 영향을 받음
 		 * 
 		 * - 공식 구하기
 		 * 단, pageLimit이 10이라고 가정
 		 * 
 		 * startPage : 1, 11, 21, ... => 
 		 * 
 		 * 즉 startPage == n * pageLimit + 1;
 		 * 
 		 * 1~10 / 10 => 몫이 0~1
 		 * 11~20 / 10 => 몫이 1~2
 		 * 마지막 숫자의 몫이 달라지게 되어 식 조정 필요
 		 * 
 		 * n = (currentPage - 1) / pageLimit
 		 * 
 		 * startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
 		 * 
 		 */
 		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
 		
 		// * endPage : 페이지 하단에 보여질 페이징 버튼의 끝 수
 		/*
 		 * startPage, pageLimit, maxPage(************) 간과 ㄴㄴㄴㄴ
 		 * 
 		 * - 공식 구하기
 		 *  단, pageLimit이 10이라는 가정
 		 *  
 		 *  startPage : 1 => endPage : 10
 		 *  startPage : 11 => endPage : 20
 		 *  
 		 *  endPage = startPage + pageLimit  - 1;
 		 *  
 		 */
 		// startPage 11, endPage 20, 그런데 maxPage가 11이라면??

 		
 		endPage = Math.min(startPage + pageLimit -1, maxPage);
 		// 여기까지 총 7개의 변수를 선언 및 초기화까지 완료
 		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
 		
 		List<Board> list = new BoardService().findAll(pi);
 		
 		request.setAttribute("list", list); // 게시글리스트
 		request.setAttribute("pi", pi); // 페이징버튼 만들으라고
 		
 		
		request.getRequestDispatcher("/WEB-INF\\views\\board\\board_list.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
