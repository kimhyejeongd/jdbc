package com.jdbc.view;

import java.util.Scanner;

import com.jdbc.controller.MemberController;

public class MainView {
	Scanner sc=new Scanner(System.in);
	public void MainMenu() {
		while(true) {
			System.out.println("====회원관리====");
			System.out.println("1. 전체회원 조회");
			System.out.println("2. 아이디로 조회");
			System.out.println("3. 이름으로 조회");
			System.out.println("4. 회원입력");
			System.out.println("5. 회원수정");
			System.out.println("6. 회원삭제");
			System.out.println("0. 프로그램 종료 ");
			System.out.println("번호 입력 : ");
			sc.nextLine();
			MemberController controller=new MemberController();
		switch(menuChoice) {
			case 1 : controller.searchAllMember(); break;
			case 2 : controller.searchMemberById(); break;
			case 3 : controller.searchMemberByName(); break;
			case 4 : controller.enrollMember(); break;
			case 5 : controller.updateMember(); break;
			case 6 : controller.deleteMember(); break;
			case 0 : System.out.println("회원관리 프로그램을 종료합니다.");
			return;
		}
			
			
			
		}
	}
}
