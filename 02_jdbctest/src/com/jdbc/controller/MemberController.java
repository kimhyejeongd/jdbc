package com.jdbc.controller;

import com.jdbc.model.dao.MemberDao;
import com.jdbc.model.dto.MemberDTO;
import com.jdbc.view.MainView;

public class MemberController {			
	
	private static MemberController controller=new MemberController();
	
	private MainView view=MainView.getMainView();
	private MemberController() {}
	private MemberDao dao=MemberDao.getMemberDao();
	public static MemberController getMemberController() {
		if(controller==null) controller=new MemberController();
		return controller;
	}
	
	public void mainMenu() {
		view.mainMenu();;
	}
	//회원등록
	public void enrollMember() {
		MemberDTO m=view.inputMemberData();
		int result=dao.enrollMember(m);
		System.out.println(result);
	}
	//회원삭제
	public void deleteMember() {
		
	}
	//회원수정
	public void updateMember() {
		
	}
	//회원전체조회
	public void searchAllMember() {
		MemberDao.getMemberDao().searchAllMember().forEach(System.out::println);
	}
	//아이디로 조회
	public void searchMemberById() {
	String id=view.searchMemberById();
	dao.searchMemberById(id);
	}
	//이름으로 조회
	public void searchMemberByname() {
		List<MemberDTO>
	}

}
