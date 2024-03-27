package com.jdbc.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.model.dto.MemberDTO;

public class MemberDao {
	private static MemberDao dao;
	
	private MemberDao() {}
	
	public static MemberDao getMemberDao() {
		if(dao==null) dao=new MemberDao();
		return dao;
	}
	// 전체 회원조회 -> student 계정에 Member테이블에 있는 전체 row를 가져와 반환
	public List<MemberDTO> searchAllMember() {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		List<MemberDTO> Members=new ArrayList<>();
		try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","STUDENT","STUDENT");
				conn.setAutoCommit(false);
				stmt = conn.createStatement();
				String sql="SELECT * FROM MEMBER";
				rs=stmt.executeQuery(sql);
				
				while(rs.next()) {
					String memberId=rs.getString("MEMBER_ID");
					String memberPwd=rs.getString("MEMBER_PWD");
					String memberName=rs.getString("MEMBER_NAME");
					char gender=rs.getString("GENDER").charAt(0);
					int age=rs.getInt("AGE");
					String email=rs.getString("EMAIL");
					String phone=rs.getString("PHONE");
					String address=rs.getString("ADDRESS");
					String[] hobby=rs.getString("HOBBY").split(",");
					LocalDate enrollDate=rs.getDate("ENROLL_DATE").toLocalDate();
					MemberDTO m=new MemberDTO(memberId,memberPwd,memberName,gender,age,email,phone,address,hobby,enrollDate);
					Members.add(m);
				}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null&&rs.isClosed()) rs.close();
				if(stmt!=null && stmt.isClosed()) stmt.close();
				if(conn!=null && conn.isClosed()) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		Members.forEach(E -> System.out.println(E));
		return Members;
	}
	public int enrollMember(MemberDTO m) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int result=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","STUDENT","STUDENT");
			conn.setAutoCommit(false);
			//pstmt=conn.createStatement();
			String sql="INSERT INTO MEMBER VALUES(?,?,?,?,?,?,?,?,?,DEFAULT)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPwd());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, String.valueOf(m.getGender()));
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmial());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, String.join(",", m.getHobby()));
			//위의 코드인데 Statment로 작성한 것
//			String sql="INSERT INTO MEMBER VALUES("
//						+"'"+m.getMemberId()+"',"
//						+"'"+m.getMemberPwd()+"',"
//						+"'"+m.getMemberName()+"',"
//						+"'"+m.getGender()+"',"
//						+"'"+m.getEmial()+"',"
//						+"'"+m.getPhone()+"',"
//						+"'"+m.getAddress()+"',"
//						+"'"+String.join(",", m.getHobby())+"',"
//						+"SYSDATE"
//				//		+"TO_DATE('"+m.getEnrollDate()+"','YYYY-MM-DD')"
//						+"DEFAULT"//"SYSDATE"
//						+")";
			result=pstmt.executeUpdate();
			if(result>0) conn.commit();
			else conn.rollback();
			
		}catch(ClassNotFoundException | SQLException e ) {
			e.printStackTrace();
		}finally  {
			try {
				if(pstmt!=null&&pstmt.isClosed()) pstmt.close();
				if(conn!=null && conn.isClosed()) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public List<MemberDTO> searchMemberById(String id){
		Connection conn=null;
		PreparedStatement prep=null;
		ResultSet rs=null;
		List<MemberDTO> Members=new ArrayList<>();
		try {
			Class.forName("oracle.jdbc.driber.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","STUDENT","STUDENT");
			conn.setAutoCommit(false);
			String sql="SELECT * FROM MEMBER WHERE MEMBER_ID='"+ id + "'";
			prep=conn.prepareStatement(sql);
			
			while(rs.next()) {
				String memberId=rs.getString("MEMBER_ID");
				String memberPwd=rs.getString("MEMBER_PWD");
				String memberName=rs.getString("MEMBER_NAME");
				char gender=rs.getString("GENDER").charAt(0);
				int age=rs.getInt("AGE");
				String email=rs.getString("EMAIL");
				String phone=rs.getString("PHONE");
				String address=rs.getString("ADDRESS");
				String[] hobby=rs.getString("HOBBY").split(",");
				LocalDate enrollDate=rs.getDate("ENROLL_DATE").toLocalDate();
				MemberDTO m=new MemberDTO(memberId,memberPwd,memberName,gender,age,email,phone,address,hobby,enrollDate);
				
				Members.add(m);
			}
		}catch(ClassNotFoundException | SQLException e ) {
			e.printStackTrace();
		}finally  {
			try {if(rs!=null&&rs.isClosed()) rs.close();
				if(prep!=null&&prep.isClosed()) prep.close();
				if(conn!=null && conn.isClosed()) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}

		
	}
		return Members;

	}
}
