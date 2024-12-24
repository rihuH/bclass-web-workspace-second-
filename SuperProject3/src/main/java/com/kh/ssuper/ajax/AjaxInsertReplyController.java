package com.kh.ssuper.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.ssuper.board.model.service.BoardService;
import com.kh.ssuper.board.model.vo.Reply;
import com.kh.ssuper.member.model.vo.Member;

@WebServlet("/insert.reply")
public class AjaxInsertReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxInsertReplyController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST
		request.setCharacterEncoding("UTF-8");
		// request로부터 값뽑기
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String content = request.getParameter("content");
		
		// session으로부터 값 뽑기
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		// VO에 담기 => Reply
		Reply reply = new Reply();
		reply.setRefBno(boardNo);
		reply.setReplyContent(content);
		reply.setReplyWriter(String.valueOf(userNo));//********************************* "" + 는 웬만하면 하지 말기. 
		// String은 불변객체니까 자리를 많이 차지함. 웹개발은 다량의 요청이 들어왔을 때를 항상 가정해야함,
		// 굳이 String을 붙여야 하면 StringBuilder같은걸 써줘야함.
		
		// Service로 요청
		int result = new BoardService().insertReply(reply);
		
		// Gson, Json -> 값을 돌려줘야하는데 값이 여러개라서 자바스크립트 객체 형태로 바꿔야 할 때 사용
		// 이번에는 돌려줘야 할 게 1개밖에 없음
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(result > 0 ? "success" : "fail");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
