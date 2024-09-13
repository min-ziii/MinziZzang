package com.test.java.file;

import java.util.Scanner;

public class Ex61_File { // 관리(컨트롤) Class
	public static void main(String[] args) {
		// Ex61_File.java
		// 1. 각 Class의 목적
		// 2. Class간의 관계
		// 	- 누가 누구를 왜 호출하는가?
		// 	- 호출할 때 뭘 넘기는가? (name, dto, ... )
		// 	- 호출 후에는 뭘 반환하는지?
		Scanner scan = new Scanner(System.in);
		ScoreService service = new ScoreService(); // 실무 담당 Class
		ScoreView view = new ScoreView();
		boolean loop = true;
		
		while(loop) {
			
			//파일 데이터 CRUD
			view.mainMenu();
			
			String sel = scan.nextLine();
			
			if (sel.equals("1")) {
				service.add();
			} else if (sel.equals("2")) {
				service.get();
			} else if (sel.equals("3")) {
				service.edit();
			} else if (sel.equals("4")) {
				service.remove();
			} else { // 종료
				loop = false;
			}
		}
		System.out.println("\n프로그램을 종료합니다.");
	}
}
