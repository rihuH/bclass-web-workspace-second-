package com.kh.ssuper.ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ajax1.do")
public class AjaxController1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxController1() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("doGet메소드 호출!");
		// 값 뽑기
		// request.getParameter()
		
		String str = request.getParameter("value"); // 키값 잘못적으면 null 출력
		System.out.println(str); // 아무것도 입력 안 하면 빈 문자열 출력
		// input요소의 val() 기본값은 빈문자열이기 때문.
		
		// 응답
		String responseData = "AJAX 요청처리를 성공했습니다.";
		// 1) 응답데이터 정보 설정*********************꼭 하세요 까먹지 말고***************
		response.setContentType("text/html; charset=UTF-8"); // encoding을 몰랐을 때 문자셋이라는 의미로
		// charset이라고 적었다가, 나중에 encoding으로 바꾸고 싶었지만 앞에 만들어둔 것들이 모두 동작을 못 하게 되기 때문에
		// 그냥 아직까지도 charset이라고 씀.
		
		// 2) 응답
		response.getWriter().print(responseData);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
