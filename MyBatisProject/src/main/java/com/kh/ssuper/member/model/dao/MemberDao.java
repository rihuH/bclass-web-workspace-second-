package com.kh.ssuper.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.ssuper.member.model.vo.Member;

public class MemberDao {

	public int insertMember(SqlSession sqlSession, Member member) {
		// 자바에서의 영속성 작업 -> sql문 실행하고 결과를 받아오는 것. pstmt.executeUpdate()가 엄밀하게 DAO의 할 일.
		
		/*
		 * SqlSession이 제공하는 메소드를 통해 SQL문을 찾아서 실행하고 결과를 받아볼 수 있음
		 * 
		 * sqlSession.sql문종류에맞는메소드("매퍼파일의namespace속성값.해당SQL문의고유한ID값", );
		 */
//		sqlSession.insert("SQL문", "SQL문을 실행할 때 필요한 데이터");
		return sqlSession.insert("memberMapper.insertMember", member);
		
	}

	public Member login(SqlSession sqlSession, Member member) {
		// selectOne() : 조회결과가 존재하지 않는다면 null을 반환
		return sqlSession.selectOne("memberMapper.login", member);
	}
	
}
