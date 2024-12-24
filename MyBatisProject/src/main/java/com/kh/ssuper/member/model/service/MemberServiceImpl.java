package com.kh.ssuper.member.model.service;

import org.apache.ibatis.session.SqlSession;

import com.kh.ssuper.common.Template;
import com.kh.ssuper.member.model.dao.MemberDao;
import com.kh.ssuper.member.model.vo.Member;

public class MemberServiceImpl implements MemberService {
	
	private MemberDao memberDao = new MemberDao();

	@Override
	public int insertMember(Member member) {
		/*
		 * Connection conn = JDBCTemplate.getConnection();
		 * int result = new MemberDao().insertMember(conn, member);
		 * if(result > 0) {JDBCTemplate.commit(conn);}
		 * JDBCTemplate.close(conn);
		 * return result;
		 */
		SqlSession sqlSession = Template.getSqlSession();
		int result =  memberDao.insertMember(sqlSession, member);
		
		if(result > 0) sqlSession.commit(); // 초기 설정을 MANAGED로 했으면 이 커밋도 자동으로 해줌.
		// 실패시에는 롤백할게 없으므로 커밋만 해줌
		sqlSession.close();
		return result;
	}

	@Override
	public Member login(Member member) {
		
		SqlSession sqlSession = Template.getSqlSession();
		member = memberDao.login(sqlSession, member);
		sqlSession.close();
		return member;
	}

}
