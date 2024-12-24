package com.kh.ssuper.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.ssuper.member.model.service.MemberService;
import com.kh.ssuper.member.model.vo.Member;

@WebServlet("/update.me")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateController() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) POST 방식 => 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 2) request로부터 값 뽑기
		// userId, userName, email, interest(checkbox)
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String[] interestArr = request.getParameterValues("interest");
		
		String interest = interestArr != null ? String.join(",", interestArr) : "";
		
		// 3) VO객체로 가공 정석은 전송용 객체를 만드는 것. VO는 특정한 경우에만 사용(어떤 경우인지 안알려줌)
		Member member = new Member();
		member.setUserId(userId);
		member.setUserName(userName);
		member.setEmail(email);
		member.setInterest(interest);
		
		// 4) 요청처리 Service메소드 호출
		int result = new MemberService().update(member);
		
		// 5) 결과값에 따라서 응답화면 지정
		if(result > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("alertMsg", "정보 수정에 성공했습니다 축하드려요~");
			//1. sendRedirect
			//2. forward
			
			// 우리에게 생긴 문제점
			// Update에 성공했는데, session의 값들이 로그인 당시 값들이므로,
			// 마이페이지에 갱신되기 전 값들이 출력됨.
			
			// 목표 => DB가서 갱신된 회원정보 들고오기
			// 현재 update에 성공한 행을 식별할 수 있는 값이 존재하나? => userId
//			new MemberService().selectOne(userId);
			// 바퀴를 또 만들어..?
			// 회원 1명 조회는 로그인 할 때 메소드 있음 / 로그인 : 아이디/ 비밀번호
			
			String userPwd = ((Member)session.getAttribute("loginUser")).getUserPwd();
			
			Member selectMember = new Member();
			selectMember.setUserId(userId);
			selectMember.setUserPwd(userPwd);
			
			Member updateMember = new MemberService().login(selectMember);
			session.setAttribute("loginUser", updateMember);
			
			
			// 1. sendRedirect
			response.sendRedirect(request.getContextPath() + "/myPage.me");
			// 1번은 마이페이지에서 처리를 하고 여기서는 보내기만 하게 되니까, 처리가 마이페이지에서만 처리하게되니까
			// 유지보수에 2번보다 용이하게 됨
			// 2. forward
//			request.getRequestDispatcher("/WEB-INF/views/common/fail_page.jsp").forward(request, response);
			// 2번의 단점. 마이페이지에서 보내준 코드와 중복코드. 중복코드는 유지보수를 힘들게 함
			
		} else {
			request.setAttribute("failMsg", "회원 정보 수정에 실패했습니다."); 
			request.getRequestDispatcher("WEB-INF/views/common/fail_page.jsp").forward(request, response);
		}
		
		
	}

}
