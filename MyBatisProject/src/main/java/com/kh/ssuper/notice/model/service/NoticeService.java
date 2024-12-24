package com.kh.ssuper.notice.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.ssuper.common.JDBCTemplate;
import com.kh.ssuper.notice.model.dao.NoticeDao;
import com.kh.ssuper.notice.model.vo.Notice;

public class NoticeService {
	
	static {
		JDBCTemplate.registDriver();
	}

	public List<Notice> findAll() {
		Connection conn = JDBCTemplate.getConnection();
		List<Notice> notices = new NoticeDao().findAll(conn);
		JDBCTemplate.close(conn);
		return notices;
	}

	public Notice find(int noticeNo) {
		Connection conn = JDBCTemplate.getConnection();
		NoticeDao nd = new NoticeDao();
		int result = nd.increaseCount(conn,noticeNo);
		Notice notice = null;
		if(result > 0) {
			notice = nd.find(conn, noticeNo);
		} else {
			JDBCTemplate.rollback(conn);
			JDBCTemplate.close(conn);
			return notice;
		}
		JDBCTemplate.commit(conn);
		JDBCTemplate.close(conn);
		return notice;
	}

	public int save(Notice notice) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new NoticeDao().save(conn, notice);
		JDBCTemplate.close(conn);
		return result;
	}

	public int update(Notice notice) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new NoticeDao().update(conn, notice);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int delete(int noticeNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new NoticeDao().delete(conn, noticeNo);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}


	


}
