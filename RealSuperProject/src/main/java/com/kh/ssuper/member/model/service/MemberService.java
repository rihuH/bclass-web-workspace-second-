package com.kh.ssuper.member.model.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.kh.ssuper.member.model.dao.MemberDao;
import com.kh.ssuper.member.model.vo.Member;

public class MemberService {

	private MemberDao md = new MemberDao();
	
	public int join(Member m) {
		InputStream stream;
		SqlSession sqlSession = null;
		try {
			stream = Resources.getResourceAsStream("mybatis-config.xml");
			sqlSession = new SqlSessionFactoryBuilder().build(stream).openSession();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return md.join(sqlSession, m);
	}

	public Member login(Member member) {
		InputStream stream;
		SqlSession session;
		try {
			stream = Resources.getResourceAsStream("mybatis-config.xml");
			session = new SqlSessionFactoryBuilder().build(stream).openSession();
			return md.login(session, member);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
