package com.kh.ssuper.member.model.service;

import static com.kh.ssuper.common.JDBCTemplate.close;
import static com.kh.ssuper.common.JDBCTemplate.commit;
import static com.kh.ssuper.common.JDBCTemplate.getConnection;
import static com.kh.ssuper.common.JDBCTemplate.registDriver;

import java.sql.Connection;

import com.kh.ssuper.common.JDBCTemplate;
import com.kh.ssuper.member.model.dao.MemberDao;
import com.kh.ssuper.member.model.vo.Member;

public class MemberService {
	
	static {
		registDriver();
	}

	public Member login(Member member) {
		// Service=> Connection객체 생성
		Connection conn = getConnection();
		
		// 2) Controller에서 넘어온 전달값과 Connection객체를 DAO메소드를 호출하면서 전달
		Member m = new MemberDao().login(conn, member);
		
		// 3) Connection 객체 반납
		JDBCTemplate.close(conn);
		
		// 4) Controller 결과 반환
		return m;
	}
	
	public int join(Member member) {
		
		Connection conn = getConnection();
		
		int result = new MemberDao().join(conn, member);
		
		if(result != 0) { commit(conn); } 
		// 성공 못 하면 트랜잭션 안 생겨서 할 필요 없음
		JDBCTemplate.close(conn);
		return result;
	}
	
	public int update(Member member) {
		Connection conn = getConnection();
		
		int result = new MemberDao().update(conn, member);
		if(result > 0) { commit(conn);}
		close(conn);
		return result;
	}
	
	public int updatePwd(int userNo, String userPwd, String updatePwd) {
		Connection conn = getConnection();
		
		int result = new MemberDao().updatePwd(conn, userNo, userPwd, updatePwd);
		
		if(result > 0) {
			commit(conn);
		}
		close(conn);
		return result;
	}
	
	public int delete(int userNo, String userPwd) {
		Connection conn = getConnection();
		int result = new MemberDao().delete(conn, userNo, userPwd);
		if(result > 0) {commit(conn);}
		close(conn);
		return result;
	}

	public int checkId(String id) {
		Connection conn = getConnection();
		int count = new MemberDao().checkId(conn, id);
		JDBCTemplate.close(conn);
		return count;
	}
	

}
