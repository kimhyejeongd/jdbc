package com.jdbc.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	//JDBC관련 공통을 사용하는 로직을
	//static메소드로 구현하는 클래서
	//connection 생성하는 메소드
	//connection, statement, preparedstatement, resultset colse메소드
	// commit, rollback
	
	public static Connection getConnection() {
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"STUDENT","STUDENT");
			conn.setAutoCommit(false);
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(Statement stmt) {
		try {
			if(stmt!=null && !stmt.isClosed()) {
				stmt.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rs) {
		try {
			if(rs!=null&& !rs.isClosed()) rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void commit(Connection conn) {
		try {
			if(conn!=null&& !conn.isClosed()) conn.commit();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void rollback(Connection conn) {
		try {
			if(conn!=null&& !conn.isClosed())conn.rollback();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
