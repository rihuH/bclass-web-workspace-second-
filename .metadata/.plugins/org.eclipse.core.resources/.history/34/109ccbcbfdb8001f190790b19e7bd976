package com.kh.ssuper.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;

import com.kh.ssuper.board.model.service.BoardService;
import com.kh.ssuper.board.model.vo.Board;
import com.kh.ssuper.common.PageInfo;
import com.kh.ssuper.common.Pagination;

/**
 * Servlet implementation class ListController
 */
@WebServlet("/list.board")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService bs = new BoardService();
		int listCount = bs.boardsCount();
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int pageLimit = 10;
		int boardLimit = 10;
		
		
		PageInfo pi = new Pagination().getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		RowBounds rowBounds = new RowBounds((currentPage - 1) * boardLimit, boardLimit);
		List<Board> list = bs.findList(rowBounds);
		
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		request.getRequestDispatcher("/WEB-INF/views/board/board_list.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
