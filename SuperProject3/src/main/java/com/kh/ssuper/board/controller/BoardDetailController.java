package com.kh.ssuper.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.ssuper.board.model.service.BoardService;
import com.kh.ssuper.board.model.vo.Attachment;
import com.kh.ssuper.board.model.vo.Board;

@WebServlet("/detail.board")
public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardDetailController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// location.href 바꾸는 것은 무조건 GET방식
		
		// 2) 뽑기
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		// 3) 가공 ~ 안해~
		// 4) 서비스 요청 - 지금 수행해야할 게 조회수증가/ 게시글 조회로 여러 개인데 어디서 요청을 두 번 할 것인가
		// 보통 service에서 dao를 두 번 호출하도록 하는 것이 일반적.
		Board board = new BoardService().findById(boardNo);
		
		/*
		 * 작업 도메인 Board
		 * insertBoard
		 * updateBoard
		 * deleteBoard
		 * selectBoard
		 * selectBoardList
		 * increaseCount
		 * searchBoard
		 * ------------------------------
		 * save
		 * update
		 * deleteById
		 * findAll
		 * findById,,,
		 * increaseCount
		 * 위 느낌 아님 아래쪽 느낌 중 선택
		 */
		
		String path = "";
		// 게시글 조회에 성공했다면
		if(board != null) {
			// 4_2) ATTACHMENT 테이블 조회
			Attachment attachment = new BoardService().selectAttachment(boardNo);
			
			// 조회된 데이터를 Attributeㅔ에 담
			request.setAttribute("board", board);
			request.setAttribute("attachment",  attachment);
			
			// 포워딩
//			request.getRequestDispatcher("\\WEB-INF\\views\\board/detail.jsp").forward(request, response);
			path = "board/detail";
		} else {
			request.setAttribute("failMsg", "게시글 조회 실패");
//			request.getRequestDispatcher("\\WEB-INF\\views\\common\\fail_page.jsp/").forward(request, response);
			path = "common/fail_page";
		}
		
		request.getRequestDispatcher("\\WEB-INF\\views\\" + path + ".jsp").forward(request, response);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
