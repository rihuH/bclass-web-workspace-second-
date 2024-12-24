package com.kh.ssuper.member.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.ssuper.common.JDBCTemplate;
import com.kh.ssuper.member.model.vo.Member;

public class MemberDao {
	private Properties prop = new Properties();
	
	public MemberDao() {
		String file = MemberDao.class.getResource("/sql/member/member-mapper.xml").getPath();
		try {
			prop.loadFromXML(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Member login(Connection conn, Member member) {
		// SELECT => ResultSet객체(unique 제약조건에 의해 한 행만 조회됨)
		// 		=> Member
		
		// 필요한 변수 세팅
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("login");
		
		// pstmt객체 생성
		try {
			pstmt = conn.prepareStatement(sql);
			
			// binding
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getUserPwd());
			
			// 쿼리문 실행 후 결과 받기
			// 쿼리문 실행 메소드
			// executeQuery => ResultSet : SELECT
			// executeUpdate => int / 0 : INSERT / UPDATE/ DELETE
			rset = pstmt.executeQuery();
			
			// rset으로부터 각각의 컬럼의값을 뽑아서 반환 Member객체 필드에 담는다.
			if(rset.next()) {
				// 각 컬럼의 값뽑기
				// rset.getInt / getString/ getDate(뽑아올 컬럼명 또는 순번)
				m = new Member(rset.getInt("USER_NO"),
							   rset.getString("USER_ID"),
							   rset.getString("USER_PWD"),
							   rset.getString("USER_NAME"),
							   rset.getString("EMAIL"),
							   rset.getString("INTEREST"),
							   rset.getDate("ENROLL_DATE"),
							   rset.getDate("MODIFY_DATE"),
							   rset.getString("STATUS"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;
	}
	
	public int join(Connection conn, Member member) {
		//INSERT -> 처리된 행의 개수
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("join");
		
		// pstmt 객체 생성(SQL문 미리 전달)
		try {
			pstmt = conn.prepareStatement(sql);
			// 파라미터 바인딩
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getUserPwd());
			pstmt.setString(3, member.getUserName());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getInterest());
			
			// SQL문 실행 후 결과받기
			// INSERT / UPDATE / DELETE => pstmt.executeUpdate();
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int update(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("update");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getUserName());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getInterest());
			pstmt.setString(4, member.getUserId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int updatePwd(Connection conn, int userNo, String userPwd, String updatePwd) {
		// UPDATE => 처리된 행의 개수
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePwd");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updatePwd);
			pstmt.setInt(2, userNo);
			pstmt.setString(3, userPwd);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int delete(Connection conn, int userNo, String userPwd) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("delete");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			pstmt.setString(2, userPwd);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int checkId(Connection conn, String id) {
		int count = 0;
		String sql = prop.getProperty("checkId");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			rset.next();
			count = rset.getInt("COUNT");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	

}
