package com.kh.ssuper.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/myPage.me")
public class MyInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MyInfoController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 내 정보 조회란 뭘까?
		request.getRequestDispatcher("/WEB-INF\\views\\member\\my_page.jsp").forward(request, response);
		// 여기 가서 회원정보 출력
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
