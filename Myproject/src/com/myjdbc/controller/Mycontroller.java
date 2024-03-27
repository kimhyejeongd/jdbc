package com.myjdbc.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.myjdbc.model.vo.Department;

public class Mycontroller {

	public static void main(String[] args) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //드라이버 등록
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","BS","BS"); //BS계정 DB접속
			conn.setAutoCommit(false); //개발자가 트렌젝션 직접 처리하게하기
			stmt= conn.createStatement(); // Connection구현체를 이용해서 SQL 문을 DB에서 실행하는 구현체를 가져오기
			String sql="SELECT * FROM DEPARTMENT"; //실행할 sql문을 작성
			rs=stmt.executeQuery(sql); //Statement 구현체가 제공하는 멤버메소드 excuteQuery(), excuteUpdate()를 이용해서 선언한 sql 문을 실행
			
		List<Department> Departments=new ArrayList<>(); //ResultSet 구현체 이용하기
		while(rs.next()) {
			char DeptId =rs.getString("DEPT_ID").charAt(0);
			String DeptTitle=rs.getString("DEPT_TITLE");
			char LocationId=rs.getString("LOCATION_ID").charAt(0);
		Department d=new Department(DeptId,DeptTitle,LocationId);
		Departments.add(d);
		}
		sql="INSERT INTO DEPARTMENT VALUES('DD','DB부','L5')";//INSERT하기
		int result=stmt.executeUpdate(sql);
		System.out.println(result>0?"입력성공":"입력실패");
		conn.commit(); //커밋하기
		
	}catch(ClassNotFoundException e) {
		e.printStackTrace();
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		try {
			if(rs!=null&& !rs.isClosed()) rs.close();
			if(stmt!=null && !stmt.isClosed()) stmt.close();
			if(conn!=null && !conn.isClosed()) conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
    }
  }
}