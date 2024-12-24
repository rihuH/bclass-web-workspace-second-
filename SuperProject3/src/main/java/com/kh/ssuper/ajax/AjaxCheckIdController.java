package com.kh.ssuper.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.ssuper.member.model.service.MemberService;

@WebServlet("/checkId.me")
public class AjaxCheckIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxCheckIdController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//GET
		//2) request로부터 값 뽑기
		String id = request.getParameter("id");
		
		// 3) VO 가공 -> 값 하나라 패스
		// 4) Service 호출
		
		// SELECT USER_ID FROM MEMBER WHERE USER_ID = '입력한거' => 없으면 리절트셋이 0행. 
		// ResultSet은 무조건 오고 0행이 옴
		// SELECT COUNT(*) FROM --- 라고 하면 1 아니면 0으로 결과가 무조건 오니까 if문 쓰지 않아도 되어서 좋다
		// 더 좋은 것은 decode같은 거 써서 nnnnn nnnny같이 돌려주는게 좋다
		// SELECT DECODE(COUNT(*))...

		int count = new MemberService().checkId(id);
		
		// 5) 결과에 따른응답
		// 중복값이 있으면 count == 1,  => "NNNNN"
		// 중복값이 없을 때 count == 0 => "NNNNY"
		
		response.getWriter().print((count > 0) ? "NNNNN" : "NNNNY");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
