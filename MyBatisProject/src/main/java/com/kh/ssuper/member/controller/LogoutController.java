package com.kh.ssuper.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout.me")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LogoutController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그아웃이란 뭘까?
		// 로그인은 session의 attribute에다가 loginUser라는 키값으로 DB에서 조회한 값들이 필드에 담긴
		// Member객체를 담았다
		
		// -> 1. loginUser키값만 지우기 (removeAttribute)
		// -> 2. session을 만료시킨다. (== 무효화한다) --> session객체 메소드 중 invalidate()
		HttpSession session = request.getSession();
		session.invalidate(); // session을 없애는 것.
		// 응답데이터 -> 웰컴파일
		// sendRedirect()
//		System.out.println(request.getContextPath());// /super가 나옴. 아래처럼 직접 적는것보다 이걸로 적어주면 파일명이 바뀌어도 된다. 
//		response.sendRedirect("/super"); // 절대경로로 적으면 서블릿매핑값 바뀌는 경우 직접 가서 바꿔야함(상대경로는 이런 문제는 없고)
		response.sendRedirect(request.getContextPath());
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
