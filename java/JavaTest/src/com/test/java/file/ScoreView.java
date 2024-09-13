package com.test.java.file;

import java.util.Scanner;

//출력 담당 Class
public class ScoreView {
	
	public void mainMenu() {
		System.out.println("==================");
		System.out.println("     성적관리");
		System.out.println("==================");
		System.out.println("1. 성적추가"); // Create
		System.out.println("2. 성적확인"); // Read
		System.out.println("3. 성적수정"); // Update
		System.out.println("4. 성적삭제"); // Delete  -> CRUD
		System.out.println("5. 종료");
		System.out.println("------------------");
		System.out.print("선택(번호): ");
	}
	
	public void subTitle(String title) {
		System.out.println();
		System.out.println("💯" + title + "💯");
	}

	public void pause() {
		System.out.println();
		System.out.print("계속하시려면 엔터 키를 눌러주세요.");
		
		Scanner scan = new Scanner(System.in);
		scan.nextLine();
		
		System.out.println();
		
	}
}
