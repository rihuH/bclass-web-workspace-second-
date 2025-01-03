package com.kh.ssuper.board.model.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.kh.ssuper.board.model.dao.BoardDao;
import com.kh.ssuper.board.model.vo.Board;
import com.kh.ssuper.board.model.vo.Category;

public class BoardService {
	private BoardDao bd = new BoardDao();
	public int boardsCount() {
		
		InputStream stream;
		int count = 0;
		try {
			stream = Resources.getResourceAsStream("/mybatis-config.xml");
			SqlSession sqlSession = new SqlSessionFactoryBuilder().build(stream).openSession(); 
			count = bd.boardsCount(sqlSession);
			sqlSession.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return count;
	}

	public List<Board> findList(RowBounds rowBounds) {
		List<Board> list = null;
		try(SqlSession sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("/mybatis-config.xml")).openSession();){
			list = bd.findList(sqlSession, rowBounds);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public Board findByNo(int boardNo) {
		InputStream stream;
		Board board = null;
		try {
			stream = Resources.getResourceAsStream("/mybatis-config.xml");
			SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession();
			board = bd.findByNo(session, boardNo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return board;
	}

	public int insert(Board b) {
		// TODO Auto-generated method stub
		InputStream stream;
		int result = 0;
		try {
			stream = Resources.getResourceAsStream("/mybatis-config.xml");
			SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession();
			result = bd.insert(session, b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public int increseCount(int boardNo) {
		InputStream stream;
		int result = 0;
		try {
			stream = Resources.getResourceAsStream("/mybatis-config.xml");
			SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession();
			result = bd.increseCount(session, boardNo);
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public List<Category> findAllCategory() {
		InputStream stream;
		List<Category> categoryList;
		try {
			stream = Resources.getResourceAsStream("/mybatis-config,xml");
			SqlSession session = new SqlSessionFactoryBuilder().build(stream).openSession();
			categoryList = bd.findAllCategory(session);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
