package com.kh.ssuper.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.ssuper.member.model.vo.Member;
import com.kh.ssuper.notice.model.service.NoticeService;
import com.kh.ssuper.notice.model.vo.Notice;

@WebServlet("/enroll.notice")
public class NoticeEnrollController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeEnrollController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member loginUser = (Member) request.getSession().getAttribute("loginUser");
		if(loginUser != null && loginUser.getUserNo() == 1) {
			request.setCharacterEncoding("UTF-8");
					
			Notice notice= new Notice();
			notice.setNoticeTitle(request.getParameter("title"));
			notice.setNoticeContent(request.getParameter("content"));
			notice.setNoticeWriter(loginUser.getUserNo());
			
			int result = new NoticeService().save(notice);
			
			
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
