package com.jdbc.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jdbc.model.vo.Member;

public class BasicJdbcTest {

	public static void main(String[] args) {
		//jdbc를 이용해서 오라클 DB에 접속하기
		//1. 오라클이 제공하는 ojdbc.jar파일을 버전에 맞춰서 다운로드
		//2. 생성한 프로젝트의 라이브러리로 다운로드한 ojdbc.jar파일을 등록
		// *classpath에 클래스들이 등록 됨.
		//3. 등록된 구현체를 이용해서 오라클 DB에 접속
		// 1) CLASSPATH에 이용할 클래스가 있는지 확인
		//  - Class의 static메소드 forName()메소드를 이용해서 이용할 클래스가 있는지 확인
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록확인 완료");
		// 2) DriverManager클래스가 제공하는 static 메소드 getConnetion()을 이용해서 Connection구현체를 가져오기
		//     - 3개의 인자값을 전달
			//	 첫번째 : 연결할 DB의 주소, String
			//   *DB의 주소는 정해진 패턴대로 사용 -> 각 벤더(DBMS회사)마다 다름
			// 오라클 : jdbc:oracle:thin:@ip주소:포트번호:sid
			// mysql : jdbc:mysql://ip주소:포트번호/연결스키마+[타입존설정가능 serverTimezone=UTCR]
			// mariaDB : jdbc:mariadb://ip주소:포트번호/연결스키마
			//   두번째 : 사용자계정명 String * (대소문자 구분안함)
			//   세번째 : 계정비밀번호 String * (대소문자 구분함)
		//Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","STUDENT","STUDENT");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","STUDENT","STUDENT");
		System.out.println("STUDENT계정 DB접속 성공");
		// 기본적으로 오토커밋되지만 트렌젝션을 개발자가 직접처리하려면
		conn.setAutoCommit(false);
		// 3) Connection구현체를 이용해서 SQL 문을 DB에서 실행하는 구현체를 가져오기
		// Statement, PreparedStatement 구현체
		// Statement : Connection.createStatement() 메소드를 이용해서 가져옴
		//Statement stmt= conn.createStatement();
		stmt= conn.createStatement();
		// 4) 실행할 sql문을 작성
		String sql="SELECT * FROM MEMBER";
		// 5) SQL문 실행하기
		// Statement 구현체가 제공하는 멤버메소드 excuteQuery(), excuteUpdate()를 이용해서 선언한 sql 문을 실행
		// excuteQuery("SQL문") : SELECT문을 실행할 때 사용 -> ResultSet구현체를 반환
		// excuteUpdate("SQL문") : DML(INSERT,UPDATE,DELETE)문을 실행할 때 사용 -> int반환(실행된 개수를 반환함)
		//ResultSet rs=stmt.executeQuery(sql);
		rs=stmt.executeQuery(sql);
		System.out.println("sql문 실행완료!");
		System.out.println(rs);
		
		//6) ResultSet 구현체 이용하기
		// select 문을 실행한 결과(resultset) 저장하는 객체로, 조회된 각 row의 데이터를 메소드를 이용해서 가져올 수 있다.
		// - row선택하기 -> rs.next() 메소드를 이용함 -> 1개 row가 지정
		// - 각 컬럼의 값을 가져오기
		//   getString(),getInt(),getDouble(),getDate().. 메소드 이용 
		// getString("컬럼명||index번호) :  char,varchar2
		// getInt("컬럼명"||index번호) : number
		// getDouble("컬럼명"||index번호) :number
		// getgetDate("컬럼명"||index번호) :date
		// ...메소드 이용
		
//		rs.next(); //1row
//		String memberId=rs.getString("member_id");
//		String memberPwd=rs.getString("member_pwd");
//		System.out.println(memberId+" "+memberPwd);
//		rs.next();//2row
//		memberId=rs.getString("member_id");
//		memberPwd=rs.getString("member_pwd");
//		System.out.println(memberId+" "+memberPwd);
		//위의 꺼 while로 사용해보기
		List<Member> members=new ArrayList<>();
		while(rs.next()) {
//			String memberId=rs.getString("member_id");
//			String memberPwd=rs.getString("member_pwd");
//			index 번호로 가져오기
//			String memberId=rs.getString(1);
//			String memberPwd=rs.getString(2);
			String memberId=rs.getString("member_id");
			String memberPwd=rs.getString("member_pwd");
			String memberName=rs.getString("member_name");
			char gender=rs.getString("gender").charAt(0);
			int age= rs.getInt("age");
			String email=rs.getString("email");
			String phone=rs.getString("phone");
			String address=rs.getString("address");
			String[] hobby=rs.getString("hobby").split(",");
			Date enrollDate=rs.getDate("enroll_date");
			Member m=new Member(memberId,memberPwd,memberName,gender,age,email,phone,address,hobby,enrollDate);
			members.add(m);
			//System.out.println(memberId+" "+memberPwd);
		}
		members.forEach(System.out::println);
		// insert문 실행하기
		//sql="INSERT INTO MEMBER VALUES('BS','BS','BS','F',19,'BS@BS.COM','12341234','경기도시흥시','운동,코딩',TO_DATE('24/02/13','YY/MM/DD'))";
		sql="INSERT INTO MEMBER VALUES('YBS','YBS','YBS','F',19,'BS@BS.COM','12341234','경기도시흥시','운동,코딩',TO_DATE('24/02/13','YY/MM/DD'))";

		//Statement DML구문을 실행할 때는 excuteUpdate()
		int result=stmt.executeUpdate(sql);
		System.out.println(result>0?"입력성공":"입력실패");
		//conn.rollback(); // 데이터 취소해버리기
		conn.commit(); //
		
		
		// 6) Connection, Statement, ResultSet구현체를 무조건 반환해줘야함
		// finally{}를 이용해서 반환해줌
		// 반환하는 메소드 : close();
		
		
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
