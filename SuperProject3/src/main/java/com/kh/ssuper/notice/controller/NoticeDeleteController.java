package com.kh.ssuper.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.ssuper.member.model.vo.Member;
import com.kh.ssuper.notice.model.service.NoticeService;

@WebServlet("/delete.notice")
public class NoticeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeDeleteController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Member loginUser = (Member) request.getSession().getAttribute("loginUser");
		if(loginUser != null && loginUser.getUserNo() == 1) {
			int result = new NoticeService().delete(Integer.parseInt(request.getParameter("noticeNo")));
			
			if(result > 0) {
				response.sendRedirect(request.getContextPath() + "/list.notice?currentPage=1");
			} else {
				request.getRequestDispatcher("/WEB-INF/views/notice/notice_list.jsp").forward(request, response);
			}
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
