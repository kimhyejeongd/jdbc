package com.jdbc.view;

import java.util.List;
import java.util.Scanner;

import com.jdbc.controller.MemberController;
import com.jdbc.model.dto.MemberDTO;

public class MainView {
	private static MainView view;
	private Scanner sc=new Scanner(System.in);
	
	public  MainView() {}

	public static MainView getMainView() {
		if(view==null) view=new MainView();
		return view;
	}
	public void mainMenu() {
		MemberController controller=MemberController.getMemberController();
		while(true) {
			System.out.println("==== 회원관리 프로그램 ====");
			System.out.println("1. 전체회원조회");
			System.out.println("2. 아이디로 조회");
			System.out.println("3. 이름으로 조회");
			System.out.println("4. 회원입력");
			System.out.println("5. 회원수정");
			System.out.println("6. 회원삭제");
			System.out.println("입력 : ");
			int menuChoice=sc.nextInt();
			sc.nextLine();
			switch(menuChoice) {
			case 1 : controller.searchAllMember();break;
			case 2 : controller.searchMemberById();break;
			case 3 : controller.searchMemberByname();break;
			case 4 : controller.enrollMember();break;
			case 5 : controller.updateMember();break;
			case 6 : controller.deleteMember();break;
			case 0 : System.out.println("회원관리 프로그램을 종료합니다");
			return;
			}
		}
	}
	public MemberDTO inputMemberData() {
		MemberDTO memberDTO=new MemberDTO();
		System.out.println("==== 회원등록 ====");
		System.out.println("아이디 : ");
		memberDTO.setMemberId(sc.nextLine());
		System.out.println("패스워드 : ");
		memberDTO.setMemberPwd(sc.nextLine());
		System.out.println("이름 : ");
		memberDTO.setMemberName(sc.nextLine());
		System.out.println("성별(M/F) : ");
		memberDTO.setGender(sc.nextLine().charAt(0));
		System.out.println("나이 : ");
		memberDTO.setAge(sc.nextInt());
		sc.nextLine();
		System.out.println("이메일 : ");
		memberDTO.setEmial(sc.nextLine());
		System.out.println("전화번호 :");
		memberDTO.setPhone(sc.nextLine());
		System.out.println("주소 : ");
		memberDTO.setAddress(sc.nextLine());
		System.out.println("취미 : ");
		memberDTO.setHobby(sc.nextLine().split(","));
		return memberDTO;
	}
	public String searchMemberById() {
		System.out.println("아이디 입력 : ");
		return sc.next();
		
	}
	public String inputData(String title) {
		System.out.println("====="+title+"조회 =====");
		System.out.println("입력 : ");
		return sc.nextLine();
	}
	public void printMember(MemberDTO m) {
		System.out.println("===== 회원조회결과 ====");
		if(m==null ) {
			System.out.println("조회된 회원이 없습니다.");
		}else {
			System.out.println(m);
		}
	}
	public void printMember(List<MemberDTO> members) {
		System.out.println("==== 회원 조회 결과 ====");
		if(members.size()>0) {
			members.forEach(System.out::println);
		//members.forEach(e->System.out.println(e));
		// for(MemberDOT m : members) {
		//	System.out.println(m); }
			
		}else {
			System.out.println("조회된 회원이 없습니다");
		}
	}
}
