package com.kh.ssuper.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.ssuper.member.model.service.MemberService;
import com.kh.ssuper.member.model.vo.Member;

@WebServlet("/sign-up.me")
public class JoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JoinController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// POST
		// 1) 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		// 회원가입이란...?
		// 사용자가 회원가입양식에 적은 값을 MEMBER테이블까지 들고가서 한 행 INSERT
		//2) request객체로부터 요청 시 전달값 뽑기
		String userId = request.getParameter("userId"); // 필수입력
		String userPwd = request.getParameter("userPwd"); // 필수입력
		String userName = request.getParameter("userName"); // 필수입력
		String email = request.getParameter("email"); // 필수입력
		String[] interestArr = request.getParameterValues("interest");
		// 테이블 한 행, 한 컬럼 값에 여러 값이 들어갈 수 있다면(1:n) 여러 개 값이 들어가는 컬럼은 다른 테이블로 분리되어야한다
		// 예로 게시글 1에 댓글 여러개 달 수 있으므로 댓글테이블 분리
		// 게시글 1에 사진 여러개 할 수 있으므로 사진테이블 분리
		// 원래 이 취미테이블도 분리되었어야 한다.
		
		String interest = (interestArr != null) ? String.join(",", interestArr) : "";
		// 선택 안 했으면 null 예외 발생할 수 있음
		
		//3) Member객체에 담기
		Member member = new Member();
		member.setUserId(userId);
		member.setUserPwd(userPwd);
		member.setUserName(userName);
		member.setEmail(email);
		member.setInterest(interest);
		
		// 4) 요청처리(Service호출)
		int result = new MemberService().join(member);
		
		// 5) 처리결과를 가지고 사용자가 보게 될 응답화면 지정
		if(result > 0) {
			// 성공 -> sendRedirect
			request.getSession().setAttribute("alertMsg", "회원가입에 성공했습니다~~"); // 로그인창에서 했던 알럿과 마찬가지로
			// 얘도 세션에 담아준다.
			response.sendRedirect(request.getContextPath());
			
		} else { // 실패
			request.setAttribute("failMsg", "회원가입에 실패 메롱~ 약오르지~");
			request.getRequestDispatcher("/WEB-INF/views/common/fail_page.jsp").forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
