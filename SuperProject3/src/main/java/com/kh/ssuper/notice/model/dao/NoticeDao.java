package com.kh.ssuper.notice.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import com.kh.ssuper.notice.model.vo.Notice;

public class NoticeDao {
	private Properties prop = new Properties();
	
	
	public NoticeDao() {
		try {
			prop.loadFromXML(new FileInputStream(NoticeDao.class.getResource("/sql/notice/notice-mapper.xml").getPath()));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public List<Notice> findAll(Connection conn) {
		List<Notice> notices = new ArrayList();
		String sql = prop.getProperty("findAll");
		// try resource는 선언하면서 써야 하므로 필드에 미리 null로 선언해두는 경우 이걸 쓸 수 없다.
		try(PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rset = pstmt.executeQuery();){
			while(rset.next()) {
				Notice notice = new Notice();
				notice.setNoticeNo(rset.getInt("NOTICE_NO"));
				notice.setNoticeTitle(rset.getString("NOTICE_TITLE"));
				notice.setNoticeContent(rset.getString("NOTICE_CONTENT"));
				notice.setNoticeWriter(rset.getInt("NOTICE_WRITER"));
				notice.setCount(rset.getInt("COUNT"));
				notice.setCreateDate(rset.getDate("CREATE_DATE"));
				
				notices.add(notice);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return notices;
	}


	public Notice find(Connection conn, int boardNo) {
		Notice notice = new Notice();
		String sql = prop.getProperty("find");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				notice.setNoticeNo(rset.getInt("NOTICE_NO"));
				notice.setNoticeTitle(rset.getString("NOTICE_TITLE"));
				notice.setNoticeContent(rset.getString("NOTICE_CONTENT"));
				notice.setNoticeWriter(rset.getInt("NOTICE_WRITER"));
				notice.setCount(rset.getInt("COUNT"));
				notice.setCreateDate(rset.getDate("CREATE_DATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notice;
	}


	public int increaseCount(Connection conn, int noticeNo) {
		int result = 0;
		String sql = prop.getProperty("increaseCount");
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}


	public int save(Connection conn, Notice notice) {
		int result = 0;
		String sql = prop.getProperty("save");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, notice.getNoticeTitle());
			pstmt.setString(2, notice.getNoticeContent());
			pstmt.setInt(3, notice.getNoticeWriter());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}


	public int update(Connection conn, Notice notice) {
		int result = 0;
		String sql = prop.getProperty("update");
		try(PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, notice.getNoticeTitle());
			pstmt.setString(2, notice.getNoticeContent());
			pstmt.setInt(3, notice.getNoticeNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}


	public int delete(Connection conn, int noticeNo) {
		int result = 0;
		String sql = prop.getProperty("delete");
		try(PreparedStatement pstmt= conn.prepareStatement(sql);){
			pstmt.setInt(1,noticeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
