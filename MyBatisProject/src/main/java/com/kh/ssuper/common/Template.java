package com.kh.ssuper.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Template {
	
	/*
	 * 기존 JDBC 
	 * public static Connection getConnection(){
	 * 	driver.properties....
	 * }
	 * 
	 * public static void close(...)..../ commit/ rollback
	 * 
	 */
	
	// 마이바티스
	public static SqlSession getSqlSession() {
		SqlSession sqlSession = null;
		
		// mybatis-config.xml 파일을 읽어서
		// 해당 DB와 접속된 sqlSession 객체를 생성해서 반환
		
		String resource = "/mybatis-config.xml";
		
		// /는 모든 src폴더의 최상위 폴더를 의미(classes)
		
		try {
			InputStream stream = Resources.getResourceAsStream(resource); // ibatis에서 제공하는 클래스. 경로가 담긴 resource를 인자로 넘겨줌
			
			// 1단계 : new SqlSessionFactoryBuilder() : SqlSessionFactoryBuilder 객체 생성
			// 2단계 : build(입력스트림);
			// 스트림으로부터 마이바티스 환경설정 파일을 읽어오면서 SqlSessionFactory 객체 생성
			// 3단계 : .openSession(false) : SqlSession객체 생성 및 트랜잭션 처리 시 autoCommit을 안 쓰겠다.
			// == 개발자가 직접 관리하겠다.
			// -> mybatis-config.xml의 <environment>의 <transactionManager type=""> 과 비교해서 둘 중 하나가 MANAGED거나 true이면 autocommit이 된다.
			// .openSession() 인자값을 전달하지 않으면 기본값이 false
			sqlSession = new SqlSessionFactoryBuilder().build(stream).openSession(false);
			
			// SqlSession sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("/mybatis-config.xml")).openSession(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sqlSession;
		
		/*
		 * 깃, svn, 엘프 (형상관리 도구들. 버전관리)
		 * 
		 * 
		 */
	}

}
