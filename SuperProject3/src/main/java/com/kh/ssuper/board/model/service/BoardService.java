package com.kh.ssuper.board.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.ssuper.board.model.dao.BoardDao;
import com.kh.ssuper.board.model.vo.Attachment;
import com.kh.ssuper.board.model.vo.Board;
import com.kh.ssuper.board.model.vo.Category;
import com.kh.ssuper.board.model.vo.Reply;
import com.kh.ssuper.common.JDBCTemplate;
import com.kh.ssuper.common.PageInfo;

public class BoardService {
	
	static {
		JDBCTemplate.registDriver();
	}
	
	public int selectListCount() {
		Connection conn = JDBCTemplate.getConnection();
		int result = new BoardDao().selectListCount(conn);
		JDBCTemplate.close(conn);
		return result;
	}
	
	public List<Board> findAll(PageInfo pi) {
		Connection conn = JDBCTemplate.getConnection();
		// 인라인 뷰 활용
		// 1) ORDER BY절은 순서가 가장 마지막인데 정렬이 끝난 상태가 필요함!
		// 일단 정렬해주는 SELECT문을 만들고 => 서브쿼리
		// 2) 서브쿼리를 FROM 절에 넣음(인라인뷰) + ROWNUM
		
		/*
		 * boardLimit이 10이라는 가정 하에
		 * currentPage == 1이면 BETWEEN 1 AND 10 
		 * currentPage == 2이면 BETWEEN 2 AND 20
		 * 시작 = currentPage - 1) * boardLimit + 1;
		 * 끝값 = 시작값 + boardLimit - 1;
		 * 
		 */
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		List<Board> boardList = new BoardDao().findAll(conn,startRow,endRow);
		// DAO에는 SQL문 실행과 결과만 있어야 하므로 DAO에서 이 계산작업을 하면 안 됨.
		// 컨트롤러는 제어만 함. 게시글 조회하는 의사결정 코드는 서비스에 가있어야함.
		
		/*
		 * 소프트웨어 개발의 층. 관심사의 분리
		 * Presentation -> Service -> Persistence -> DB
		 * 분리 이유 -> 유지보수, 확장성(웹에서 모바일로 확장이라거나), 
		 */
		JDBCTemplate.close(conn);
		
		return boardList;
	}
	
	public List<Category> selectCategory(){
		Connection conn = JDBCTemplate.getConnection();
		
		List<Category> list = new BoardDao().selectCategory(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public int insert(Board board, Attachment attachment) {
		Connection conn = JDBCTemplate.getConnection();
		
		BoardDao bd = new BoardDao();
		// 1) Board테이블에 INSERT
		int boardResult = bd.insertBoard(conn, board);
		
		int attachmentResult = 1;
		// 2) Attachment테이블에 INSERT
		if(attachment != null) {
			bd.insertAttachment(conn, attachment);
			// ref 보드번호를 SEQ_BNO.CURRVAL로 채운다. 마지막으로 성공한 .NEXTVAL의 값으로 COMMIT되기 전이라도 같은 트랜잭션 안에 있으므로 사용할 수 있음.
		}
		
		// 3) 트랜잭션 처리
		// boardResult가 성공이고 attachmentResult도 성공이면 commit
		if((boardResult * attachmentResult) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return (boardResult * attachmentResult); // 웬만하면 1, 0으로 결과 나오는게 좋기떄문에 쌤의 선호도
		
	}
	
	public Board findById(int boardNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		BoardDao boardDao = new BoardDao();
		Board board = null;
		// 1. 조회수 증가
		int result = boardDao.increaseCount(conn, boardNo);
		// 2. 조회수 증가에 성공했다면 한 행 조회
		if(result > 0) { 
			board = boardDao.findById(conn, boardNo);
			JDBCTemplate.commit(conn);
		}
		JDBCTemplate.close(conn);
		return board;
	}
	
	public Attachment selectAttachment(int boardNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		Attachment attachment = new BoardDao().selectAttachment(conn, boardNo);
		
		JDBCTemplate.close(conn);
		
		return attachment;
	}
	
	public Board selectBoard(int boardNo) {
		Connection conn = JDBCTemplate.getConnection();
		Board board = new BoardDao().findById(conn, boardNo);
		JDBCTemplate.close(conn);
		return board;
	}
	
	public int update(Board board, Attachment attachment) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().updateBoard(conn, board);
		int attachmentResult = 1;
		// ATTACHMENT는???
		// 새롭게 파일을 첨부했을 경우
		if(attachment != null) {
			if(attachment.getFileNo() != 0) {
				// 기존에 첨부파일이 존재했을 경우 => UPDATE
				attachmentResult = new BoardDao().updateAttachment(conn, attachment);
			} else {
				// 기존에 첨부파일이 존재하지 않았을 경우 => INSERT
				attachmentResult = new BoardDao().insertNewAttachment(conn, attachment);
			}
		}// else일 때는 할 거 없으므로 없어도 됨.
		
		// 둘 다 성공했을 경우에만 commit;
		// 하나라도 실패했다 rollback;
		
		if((result * attachmentResult) > 0) {
			JDBCTemplate.commit(conn);
		}  else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result * attachmentResult;
	}

	public int delete(int int1) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertThumbnailBoard(Board board, List<Attachment> list) {
		
		Connection conn = JDBCTemplate.getConnection();
		// 한 개의 트랜잭션에 최소 2개에서 최대 5개까지의 DML구문이 만들어질 것
		
		int result = new BoardDao().insertThumnailBoard(conn, board);
		
		if(result > 0) {
			result = new BoardDao().insertAttachmentList(conn, list);
		}
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}

	public List<Board> selectList() {
		Connection conn = JDBCTemplate.getConnection();
		List<Board> list = new BoardDao().selectList(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public List<Attachment> selectImageList(int boardNo) {
		Connection conn = JDBCTemplate.getConnection();
		List<Attachment> list = new BoardDao().selectImageList(conn, boardNo);
		JDBCTemplate.close(conn);
		return list;
	}

	public List<Reply> selectReplyList(int boardNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		List<Reply> replyList = new BoardDao().selectReplyList(conn, boardNo);
		JDBCTemplate.close(conn);
		return replyList;
	}

	public int insertReply(Reply reply) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new BoardDao().insertReply(conn, reply);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}





	

}
