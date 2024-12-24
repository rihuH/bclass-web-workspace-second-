package com.kh.ssuper.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.ssuper.board.model.service.BoardService;
import com.kh.ssuper.board.model.vo.Attachment;
import com.kh.ssuper.board.model.vo.Board;

@WebServlet("/detail.thumbnail")
public class ThumbnailDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ThumbnailDetailController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		Board board = new BoardService().findById(boardNo);
		// 기존에 만들어 두었던 findById() 호출 시
		// 일반 게시글 조회할 땐 카테고리 컬럼에 null값이 들어간 행이 없었지만
		// 사진 게시글 조회할 땐 카테고리 컬럼 값이 null이라서 innerJoin으로 조회 불가능
		// => CATEGORY_NO컬럼을 기준으로 일치하는 컬럼, 그렇지 않은 컬럼 BOARD는 다 조회할거야
		// 기존의 innerJoin 구문을 OuterJoin으로 수정.
		
		// Attachment테이블 조회
		List<Attachment> list = new BoardService().selectImageList(boardNo);
		
		request.setAttribute("list", list);
		request.setAttribute("board", board);
		
		request.getRequestDispatcher("/WEB-INF/views/board/thumbnail_detail.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
