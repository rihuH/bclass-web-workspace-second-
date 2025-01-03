package com.kh.ssuper.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.kh.ssuper.board.model.vo.Board;
import com.kh.ssuper.board.model.vo.Category;

public class BoardDao {

	public int boardsCount(SqlSession sqlSession) {
		return sqlSession.selectOne("boardMapper.boardsCount");
	}

	public List<Board> findList(SqlSession sqlSession, RowBounds rowBounds) {
		return sqlSession.selectList("boardMapper.findList", null, rowBounds);
	}

	public Board findByNo(SqlSession session, int boardNo) {
		// TODO Auto-generated method stub
		return session.selectOne("boardMapper.findByNo", boardNo);
	}

	public int insert(SqlSession session, Board b) {
		// TODO Auto-generated method stub
		return session.insert("boardMapper.insert", b);
	}

	public int increseCount(SqlSession session, int boardNo) {
		// TODO Auto-generated method stub
		return session.update("boardMapper.increseCount", boardNo);
	}

	public List<Category> findAllCategory(SqlSession session) {
		
		return session.selectList("boardMapper.findAllCategory");
	}

}
