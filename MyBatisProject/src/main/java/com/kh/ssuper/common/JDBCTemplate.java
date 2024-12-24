package com.kh.ssuper.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties; 

public class JDBCTemplate {
	
	public static void registDriver() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	} 

	public static Connection getConnection() {
		
		Connection conn = null;
		Properties prop = new Properties();
		try {
			
			// 기존 사용하던 걸 그대로 복사해서 사용하면 FileNotFoundException이 발생하게 됨.
			// webapp에 있는 것들이 웹상에 올라가게 됨.
			// 그리고 webapp 안에 있는 properties가 필요함. 기존 경로와 이 경로가 틀려서 발생하게 된다.
			// (우리가 실제 작업하고 있는 파일은 웹 상에 올라가는 것과는 무관)
			// driver.properties 를 찾아갈 수 있는 경로를 지정해줘야함.
			
			// webapp에 있는 JDBCTemplate에서 출발
			String filePath = JDBCTemplate.class.getResource("/sql/driver/driver.properties").getPath();// 처음 / 는 classes를 나타낸다
			// 처음의 / 는 content directory를 나타낸다. 
			// 이 메소드가 없으면 경로를 찾지 못한다.
			
			prop.load(new FileInputStream(filePath)); // IOException 
			
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
			conn.setAutoCommit(false);
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	// Connection 객체를 이용해서 commit 시켜주는 메소드
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) conn.commit();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Connection 객체를 이용해서 rollback 시켜주는 메소드
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) conn.rollback();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// JDBC용 객체를 반납시켜주는 메소드(각 객체별로)
	
	// Connection 객체를 전달받아서 반납시켜주는 메소드
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Statement 객체를 전달받아서 반납시켜주는 메소드
	// => 다형성 적용으로 인해 PreparedStatement객체도 Statement타입으로 받을 수 있음
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	// ResultSet 객체를 전달받아서 반납시켜주는 메소드
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) rset.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
