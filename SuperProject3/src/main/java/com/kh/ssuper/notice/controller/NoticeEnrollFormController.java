package com.kh.ssuper.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.ssuper.member.model.vo.Member;

@WebServlet("/enrollForm.notice")
public class NoticeEnrollFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeEnrollFormController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member loginUser = (Member) request.getSession().getAttribute("loginUser");
		String path ="/WEB-INF/views/notice/";
		if(loginUser != null && loginUser.getUserNo() == 1) {
			path += "enroll_form.jsp";
		} else {
			request.setAttribute("failMsg", "관리자만 글을 쓸 수 있습니다.");
			path += "fail_page.jsp";
		}
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
