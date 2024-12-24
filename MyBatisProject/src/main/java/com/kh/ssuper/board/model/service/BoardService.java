package com.kh.ssuper.board.model.service;

import java.util.List;
import java.util.Map;

import com.kh.ssuper.board.model.vo.Board;
import com.kh.ssuper.board.model.vo.Reply;
import com.kh.ssuper.common.PageInfo;

public interface BoardService {
	
	// 게시글 관련 기능
	// 1. 목록조회(페이징 처리)
	
	int selectListCount();
	
	List<Board> selectList(PageInfo pi);
	
	// 2. 상세조회
	
	int increaseCount(int boardNo);
	
	Board selectBoard(int boardNo);
	
	List<Reply> selectReplyList(int boardNo);
	
	// 댓글조회 관련
	// 1. 동기식 요청으로 게시글의 정보를 조회할 때 select를 한 번 더 수행해서 조회
	// 2. 동기식 요청으로 조회하되 게시글의 정보와 한꺼번에 조회해서 가져갈 예정
	
	//3. 검색서비스
	int searchedCount(Map<String, String> map);
	
	List<Board> selectSearchList(PageInfo pi, Map<String, String> map);

}
