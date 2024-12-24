package com.kh.ssuper.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.ssuper.common.PageInfo;
import com.kh.ssuper.notice.model.service.NoticeService;
import com.kh.ssuper.notice.model.vo.Notice;

@WebServlet("/list.notice")
public class NoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeListController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeService ns = new NoticeService();
		List<Notice> notices = ns.findAll();
		if(notices.isEmpty()) {
			request.setAttribute("failMsg", "조회할 글이 없습니다.");
			request.getRequestDispatcher("WEB-INF/views/common/fail_page.jsp").forward(request, response);
		}
		
		int listCount = notices.size();
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int pageLimit = 10; // 보여줄 최대 페이지
		int boardLimit = 10; // 보여줄 게시글 수
		
		int startPage = (currentPage - 1) / pageLimit * pageLimit + 1; // 시작 페이지 버튼
		int maxPage = (int)Math.ceil((double)listCount/boardLimit);
		int endPage = Math.min(startPage + pageLimit - 1, maxPage);
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		int startRow = (boardLimit)*(currentPage-1); // currentPage에 해당하는 시작 인덱스번호
		int endRow = Math.min(startRow + boardLimit, notices.size()); // 보여줄 끝 번호 + 1
		request.setAttribute("pi", pi);
		request.setAttribute("list",notices.subList(startRow, endRow));
		request.getRequestDispatcher("\\WEB-INF\\views\\notice\\notice_list.jsp").forward(request, response);
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
