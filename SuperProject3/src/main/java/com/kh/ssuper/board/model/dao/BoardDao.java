package com.kh.ssuper.board.model.dao;

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

import com.kh.ssuper.board.model.vo.Attachment;
import com.kh.ssuper.board.model.vo.Board;
import com.kh.ssuper.board.model.vo.Category;
import com.kh.ssuper.board.model.vo.Reply;
import com.kh.ssuper.common.JDBCTemplate;

public class BoardDao {
	
	Properties prop = new Properties();

	public BoardDao() {
		try {
			prop.loadFromXML(new FileInputStream(BoardDao.class.getResource("/sql\\board\\board-mapper.xml").getPath()));
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int selectListCount(Connection conn) {
		int listCount = 0;
		String sql = prop.getProperty("selectListCount");

		try(PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rset = pstmt.executeQuery();){
			// 자원반납 편하게 하려고
			
			rset.next(); 
			listCount = rset.getInt("COUNT(*)");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCount;
	}
	
	public List<Board> findAll(Connection conn, int startRow, int endRow){
		List<Board> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("findAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board board = new Board();
				board.setBoardNo(rset.getInt("BOARD_NO"));
				board.setBoardTitle(rset.getString("BOARD_TITLE"));
				board.setBoardWriter(rset.getString("USER_ID"));
				board.setCount(rset.getInt("COUNT"));
				board.setCreateDate(rset.getDate("CREATE_DATE"));
				board.setCategory(rset.getString("CATEGORY_NAME"));
				
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	
	public List<Category> selectCategory(Connection conn){
		List<Category> list = new ArrayList();
		String sql = prop.getProperty("selectCategory");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery();) {
			while(rset.next()) {
				list.add(new Category(rset.getInt("CATEGORY_NO"), rset.getString("CATEGORY_NAME")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	public int insertBoard(Connection conn, Board board) {
		int result = 0;
		String sql = prop.getProperty("insertBoard");
		try(PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1,Integer.parseInt(board.getCategory()));
			pstmt.setString(2, board.getBoardTitle());
			pstmt.setString(3, board.getBoardContent());
			pstmt.setInt(4, Integer.parseInt(board.getBoardWriter()));
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int insertAttachment(Connection conn, Attachment attachment) {
		int result = 0;
		String sql = prop.getProperty("insertAttachment");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, attachment.getOriginName());
			pstmt.setString(2, attachment.getChangeName());
			pstmt.setString(3, attachment.getFilePath());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int increaseCount(Connection conn, int boardNo) {
		int result = 0;
		String sql = prop.getProperty("increaseCount");
		try(PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Board findById(Connection conn, int boardNo) {
		Board board = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("findById");
		
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				board = new Board();
				board.setBoardNo(rset.getInt("BOARD_NO"));
				board.setBoardTitle(rset.getString("BOARD_TITLE"));
				board.setBoardContent(rset.getString("BOARD_CONTENT"));
				board.setCategory(rset.getString("CATEGORY_NAME"));
				board.setBoardWriter(rset.getString("USER_ID"));
				board.setCreateDate(rset.getDate("CREATE_DATE"));	
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return board;
		
	}
	
	public Attachment selectAttachment(Connection conn, int boardNo) {
		Attachment attachment = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				attachment = new Attachment();
				attachment.setFileNo(rset.getInt("FILE_NO"));
				attachment.setOriginName(rset.getString("ORIGIN_NAME"));
				attachment.setChangeName(rset.getString("CHANGE_NAME"));
				attachment.setFilePath(rset.getString("FILE_PATH"));	
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return attachment;
	}

	public int updateBoard(Connection conn, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(board.getCategory()));
			pstmt.setString(2, board.getBoardTitle());
			pstmt.setString(3, board.getBoardContent());
			pstmt.setInt(4, board.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateAttachment(Connection conn, Attachment attachment) {
		int result = 0;
		String sql = prop.getProperty("updateAttachment");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, attachment.getOriginName());
			pstmt.setString(2, attachment.getChangeName());
			pstmt.setString(3, attachment.getFilePath());
			pstmt.setInt(4, attachment.getFileNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return result;
	}

	public int insertNewAttachment(Connection conn, Attachment attachment) {
		int result = 0;
		String sql = prop.getProperty("insertNewAttachment");
		// !하나면 잘 못보고 지나칠 수 있으니까 !!!로 쓰기도 한다.
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql);){
			
			pstmt.setInt(1, attachment.getRefBno());
			pstmt.setString(2, attachment.getOriginName());
			pstmt.setString(3, attachment.getChangeName());
			pstmt.setString(4, attachment.getFilePath());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int insertThumnailBoard(Connection conn, Board board) {
		int result = 0;
		String sql = prop.getProperty("insertThumbnailBoard");
		try(PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setInt(3, Integer.parseInt(board.getBoardWriter()));
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int insertAttachmentList(Connection conn, List<Attachment> list) {
		int result = 0;
		String sql = prop.getProperty("insertAttachmentList");
		try(PreparedStatement pstmt = conn.prepareStatement(sql);){
			// 리스트 요소의 개수만큼 Attachment테이블에 행을 추가
			for(Attachment at : list) {
				
				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setString(3, at.getFilePath());
				pstmt.setInt(4, at.getFileLevel());
				
				result = pstmt.executeUpdate();
				if(result == 0) {
					break;
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Board> selectList(Connection conn) {
		List<Board> list = new ArrayList();
		String sql = prop.getProperty("selectList");
		try(PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rset = pstmt.executeQuery();){
			while(rset.next()) {
				Board board = new Board();
				board.setBoardNo(rset.getInt("BOARD_NO"));
				board.setBoardTitle(rset.getString("BOARD_TITLE"));
				board.setCount(rset.getInt("COUNT"));
				board.setImagePath(rset.getString("IMAGEPATH"));
				
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Attachment> selectImageList(Connection conn, int boardNo) {
		List<Attachment> list = new ArrayList();
		String sql = prop.getProperty("selectAttachment"); // 이미 만들어둠
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Attachment at = new Attachment();
				at.setFileNo(rset.getInt("FILE_NO"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
				at.setChangeName(rset.getString("CHANGE_NAME"));
				at.setFilePath(rset.getString("FILE_PATH"));
				list.add(at);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public List<Reply> selectReplyList(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Reply> list = new ArrayList();
		String sql = prop.getProperty("selectReplyList");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Reply reply = new Reply();
				reply.setReplyNo(rset.getInt("REPLY_NO"));
				reply.setReplyContent(rset.getString("REPLY_CONTENT"));
				reply.setReplyWriter(rset.getString("USER_ID"));
				reply.setCreateDate(rset.getString("CREATE_DATE"));
			    
				list.add(reply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int insertReply(Connection conn, Reply reply) {
		int result = 0;
		String sql = prop.getProperty("insertReply");
		try(PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, reply.getReplyContent());
			pstmt.setInt(2, reply.getRefBno());
			pstmt.setInt(3, Integer.parseInt(reply.getReplyWriter()));
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	

}
