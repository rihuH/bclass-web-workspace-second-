package com.kh.ssuper.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/ajax2.do")
public class AjaxController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxController2() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// POST => 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 값 뽑기
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		
		// VO가공 -> Service!
		// 결과 응답
		
		/*
		// 인코딩설정(필수)]
		response.setContentType("text/html; charset=UTF-8");
		// 값 넘기기 -- 출력
		String responseData = "이름 : " + name + ", 숫자 : " + age;
		response.getWriter().print(responseData);
		*/
		
		// AJAX를 활용해서 실제 값을 여러 개 응답하고 싶다.
		// JSON 제이슨 이용.
		// JavaScript Object Notation 
		// 데이터 전송 시 이용할 수 있는 포맷형식 중 하나.
		// 1. 자바스크립트의 배열 객체. => [value, value, value..]
		// 2. 자바스크립트의 일반 객체 =>  {key:value, key:value, key:value...}
		
		// ['홍길동', 15] 자바에서 자바스크립트 객체처럼 모양을 만들어야함
//		String responseData = "['" + name + "'," + age + "]";
//		System.out.println(responseDate);

//		response.setContentType("text/html; charset=UTF-8");
//		response.setContentType("application/json; charset=UTF-8");
//		response.getWriter().print(responseData); // 하지만 모양만 자바스크립트 배열이지 그냥 문자열
		// 왜냐면 위에서 setContentType을 text라고 했기 때문
		// 그렇지만 application/json으로 바꿔도 json은 자바스크립트에서 제공하므로 자바에선 제공하지 않는다
		// 따라서 사용하고 싶으면 라이브러리를 다운받아야 한다.
		
		/*
		 * JSON형태로 처리 시 사용하는 클래스
		 * => 자바에서는 기본적으로 제공 X => 라이브러리 추가 필요
		 * 
		 * 1. JSONArray
		 * 2. JSONObject
		 */
		
		
		JSONArray responseData = new JSONArray(); // [] 자바스크립트 배열처럼 생김.
		// 요소추가 => add()
		responseData.add(name); // ['홍길동']
		responseData.add(age); // ['홍길동', 15]
		// list랑 set이랑 사용법이 비슷
		
		
		JSONObject obj = new JSONObject();
		// 요소추가 => put()
		obj.put("name", name);
		obj.put("age", age);
	
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(obj);
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
