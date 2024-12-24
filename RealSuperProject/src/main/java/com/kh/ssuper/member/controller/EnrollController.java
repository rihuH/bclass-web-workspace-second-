package com.kh.ssuper.member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.ssuper.member.model.service.MemberService;
import com.kh.ssuper.member.model.vo.Member;

@WebServlet("/enroll.me")
public class EnrollController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EnrollController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("id");
		String userPwd = request.getParameter("pwd");
		String userName = request.getParameter("name");
		String email = request.getParameter("email");
		String interest = String.join(",", request.getParameterValues("interest"));
		
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		m.setUserName(userName);
		m.setEmail(email);
		m.setInterest(interest);
		
		int result = new MemberService().join(m);
		
		String msg = "";
		String forwa = "/WEB-INF/views/";
		if(result > 0) {
			msg = "성공!";
			forwa += "include/main.jsp";
		} else {
			forwa += "common/fail_page.jsp";
		}
		request.getSession().setAttribute("msg", msg);
		request.getRequestDispatcher(forwa).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
