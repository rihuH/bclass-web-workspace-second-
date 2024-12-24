package com.kh.ssuper.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.ssuper.member.model.vo.Member;
import com.kh.ssuper.notice.model.service.NoticeService;

@WebServlet("/updateForm.notice")
public class NoticeUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeUpdateFormController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member loginUser = (Member) request.getSession().getAttribute("loginUser");
		if(loginUser != null && loginUser.getUserNo() == 1) {
			request.setAttribute("notice", new NoticeService().find(Integer.parseInt(request.getParameter("noticeNo"))));
			request.getRequestDispatcher("/WEB-INF/views/notice/update_form.jsp").forward(request, response);
		} 
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
