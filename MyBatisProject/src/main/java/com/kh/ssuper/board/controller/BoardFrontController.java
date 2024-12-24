package com.kh.ssuper.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.notice") // 모든 .board요청이 오면 출동함. 매핑값이 맞는 정확한 서블릿이 있으면 거기로 가고 여기로 안 옴.
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardFrontController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI(); // 어떤 요청에 의해 출동했는가
		System.out.println(uri);
		System.out.println("출력이");
		
//		String mapping = uri.substring .. ("/ " 로 잘라서 .....
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
