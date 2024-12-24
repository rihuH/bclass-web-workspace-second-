package com.kh.ssuper.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.ssuper.member.model.vo.Member;

public class MemberDao {

	public int join(SqlSession sqlSession, Member m) {
		return sqlSession.insert("memberMapper.join", m);
	}

	public Member login(SqlSession session, Member member) {
		return session.selectOne("memberMapper.login", member);
	}

}
