package com.test.java.file;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class Ex54_File {
	
	private static int fileCount; // 파일 갯수
	private static int dirCount; // 폴더 갯수
	private static long dirSize; // 파일 크기의 합
	
	public static void main(String[] args) {
		// Ex54_File.java
		/*
		  	1. 파일/디렉토리 조작
		  	- 윈도우 탐색기로 하는 행동을 자바로 구현해보자.
		  	- 파일/디렉토리 정보 열람
		  	- 생성, 수정, 삭제, 이동 등...
		  	
		  	2. 파일 입출력
		   	
		 */
//		m1();
//		m2();
//		m3();
//		m4();
//		m5();
//		m6();
//		m7();
//		m8();
//		m9();
//		m10();
//		m11();
//		m12();
//		m13();
		m14(); // 재귀구조 이해해야 함.
	} // main

	

	private static void m14() { // static method는 this를 못쓴다. 
		
		// 폴더의 크기를 알고싶을 때
		// 1. 크기: 680MB (713,338,114 바이트)
		// 2. 내용: 파일 11,374, 폴더 2,268 -> 본인 폴더는 아마 제외됐을수도.
		String path = "C:\\class\\dev\\eclipse";
		File dir = new File(path);
		
		if (dir.exists()) {
			count(dir);
			System.out.printf("파일 개수: %,d개\n", fileCount);
			System.out.printf("폴더 개수: %,d개\n", dirCount);
			System.out.printf("폴더 크기: %,dB\n", dirSize);
		}
	}



	public static void count(File dir) {
		File[] list = dir.listFiles();
		
		for (File file : list) {
			if (file.isFile()) {
				fileCount++;
				dirSize += file.length();
			}
		}
		for (File subdir : list) {
			if (subdir.isDirectory()) {
				dirCount++;
				count(subdir);					
			}
		}
	}
	
	private static void m13() {
		
		// 폴더 크기? -> 재귀 구조
		String path = "C:\\class\\code\\java\\file";
		File dir = new File(path);
		
		int count = 0; // 파일 개수
		
		if (dir.exists()) {
			File[] list = dir.listFiles(); // file 폴더 (부모)
			
			for (File file : list) { // 목록에서 file만 찾아서 count
				if (file.isFile()) {
				count++;
				}
			}
			for (File subdir : list) {
				if (subdir.isDirectory()) {
					// 자식 폴더 내용
					File[] sublist = subdir.listFiles(); // 자식 폴더
					
					for (File subfile : sublist) {
						if (subfile.isFile()) {
							count++;
						}
					}
					for (File subsubdir : sublist) {
						if (subsubdir.isDirectory()) {
							// 자식의 자식 폴더
							File subsublist[] = subsubdir.listFiles();
							
							for (File subsubfile : subsublist) {
								if (subsubfile.isFile()) {
									count++;
								}
							}
						}
					}
				}
			}
		}
		System.out.printf("파일 개수: %d개\n", count);
	}

	private static void m12() {

		// 특정 폴더의 내용물(파일, 자식폴더)을 열람
		String path = "C:\\class\\dev\\eclipse";
		File dir = new File(path);
//		
//		if (dir.exists()) { // 파일이 실존하는지 검사
//			// 1. dir.list()
//			// 2. dir.listfile() (보통 2번을 더 많이 씀)
//			String[] list = dir.list(); // 파일명과 디렉토리명을 문자열 배열로 넘겨줌
//			
//			for (String item : list) {
//				// item이 파일인지 폴더인지 구분해야 한다.
//				System.out.println(item);
//				
//				File subFile = new File(path + "\\" + item);
//				System.out.println(subFile.isFile() ? "파일" : "폴더");
//				System.out.println();
//			}
//		} else {
//			System.out.println("폴더가 없습니다.");
//		}
		
		File[] list = dir.listFiles(); // 자식들을 File 객체로 가공 후 리턴
		
//		for (File item : list) {
//			System.out.println(item.getName());
//			System.out.println(item.isFile());
//		}
		
		for (File item : list) { // 하나하나 물어봐야 한다
			if (item.isDirectory()) {
				System.out.printf("📁 %s\n", item.getName());
			}
		}
		for (File item : list) { // 하나하나 물어봐야 한다
			if (item.isFile()) {
				System.out.printf("🗒️ %s\n", item.getName());
			}
		}
		
	}

	private static void m11() {
		
		// 폴더 삭제
		String path = "C:\\class\\code\\java\\file\\aaa";
		File dir = new File(path);
		
		System.out.println(dir.delete()); // 빈 폴더만 지울 수 있다.
		
	}

	private static void m10() {
		
		// 폴더명을 바꾸거나 폴더 위치를 이동
		// - renameTo()를 쓴다.
		String path = "C:\\class\\code\\java\\file\\member";
		String path2 = "C:\\class\\dev\\member";
		
		File dir = new File(path);
		File dir2 = new File(path2);
		
		System.out.println(dir.renameTo(dir2));
		
	}

	private static void m9() {
		
		// 요구사항) 일정 관리를 하고 있는데, 날짜 별로 폴더를 만들어 달라.
		// 2024-01-01 ~ 2024-12-31 -> 폴더를 366개 생성
		
		Calendar c = Calendar.getInstance();
		c.set(2024, 0, 1);
		// 왜 month는 0부터 시작인가?
		// month의 이름은 숫자의 의미가 아니라 특정 인명인 경우가 많기 때문에, 이전부터 프로그래밍 언어에서 배열로 취급해왔다.
		// Week of the day(요일)도 month와 마찬가지 경우.
//		System.out.println(c.getActualMaximum(Calendar.DAY_OF_YEAR)); // c에 저장된 시각 기준. DAY_OF_YEAR는 1년이 며칠인지 알려주는 keyword
		
		for (int i = 0; i < c.getActualMaximum(Calendar.DAY_OF_YEAR); i++) {
			String path = String.format("C:\\class\\code\\java\\file\\일정\\%tF", c);
			File dir = new File(path);
			dir.mkdirs();
			System.out.printf("%tF\n", c); // YYYY-MM-DD 형식으로 출력하고
			c.add(Calendar.DATE, 1); // c에서 1일 더하기
		}
		
		
	}

	private static void m8() {
		
		// 요구사항) 회원 정보를 가지고 있는데, 회원 이름으로 개인 폴더를 만들어달라.
		
		String[] member = {"홍길동", "아무개", "강아지", "고양이", "병아리"};
		for (int i =0; i < member.length; i++) {
			String path = "C:\\class\\code\\java\\file\\회원\\" + member[i];
			File dir = new File(path);
			System.out.println(dir.mkdir());
		}
		
	}

	private static void m7() {

		// 폴더 조작
//		String path = "C:\\class\\code\\java\\file\\aaa";
//		File dir = new File(path);
//		
//		System.out.println(dir.mkdir()); // 폴더 하나만
		
		String path = "C:\\class\\code\\java\\file\\bbb\\ccc\\ddd";
		File dir = new File(path);
		
		System.out.println(dir.mkdirs()); // 존재하지 않는 모든 폴더를 전부
		
	}

	private static void m6() {
		
		// 파일 삭제
		String path = "C:\\class\\code\\java\\file\\data.txt";
		
		File file = new File(path); // 일단 참조부터 시작
		
		System.out.println(file.delete());
		
	}

	private static void m5() {

		// 파일 이동
		String path = "C:\\class\\code\\java\\file\\jumsu.txt"; // 현재 상태
		String path2 = "C:\\class\\dev\\score.txt"; // 되고싶은 상태
		
		File file = new File(path);
		File file2 = new File(path2);
		
		//보통 삭제: 휴지통 폴더로 이동
		//진짜 삭제: 복구 불가능
		System.out.println(file.renameTo(file2));
	}

	private static void m4() {
		
		//파일명 수정
		String path = "C:\\class\\code\\java\\file\\score.txt";
		String path2 = "C:\\class\\code\\java\\file\\jumsu.txt";
		
		File file = new File(path);
		File file2 = new File(path2);
		
		System.out.println(file.renameTo(file2)); // file을 file2로 바꾼다
	}

	private static void m3() {

		// 파일 조작-> 생성, 이동, 파일명 수정, 삭제 등...
		
		// 생성
		String path = "C:\\class\\code\\java\\file\\score.txt";
		
		File file = new File(path);
		
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static void m2() {
		
		// 폴더는 파일이다.
		
		// C:\class\code\java\file
		
		// 폴더 경로
		String path = "C:\\class\\code\\java\\file";
		// 폴더 참조 객체
		File dir = new File(path);
		
		if (dir.exists()) {
			System.out.println(dir.getName());
			System.out.println(dir.isFile());
			System.out.println(dir.isDirectory());
			System.out.println(dir.length());
			System.out.println(dir.getAbsolutePath());
			System.out.println(dir.lastModified());
			System.out.println(dir.isHidden());
			System.out.println(dir.canRead());
			System.out.println(dir.canWrite());
			System.out.println(dir.getParent());
		}
		
	}

	private static void m1() {
		
		// C:\class\code\java\file\data.txt
		
		// 자바 프로그램 -> 접근 -> 외부 파일
		// 1. 외부 파일을 참조하는 객체를 생성 -> 중계인(대리인)
		// 2.참조 객체를 조작해 실제 파일에 적용
		
		// 파일 경로
		String path = "C:\\class\\code\\java\\file\\data.txt";
		
		// 파일 참조 객체 -> java.io.File 클래스
		File file = new File(path);
		
		if (file.exists()) {
			// 파일 정보
			System.out.println(file.getName()); // data.txt
			System.out.println(file.isFile()); // true
			System.out.println(file.isDirectory()); // false
			System.out.println(file.length()); // 파일 크기(byte)
			System.out.println(file.getAbsolutePath()); // C:\class\code\java\file\data.txt
			System.out.println(file.lastModified()); // 최종 수정 시간, 1720505510854
			
			Calendar c1 = Calendar.getInstance();
			c1.setTimeInMillis(file.lastModified()); // tick->Calendar
			System.out.printf("%tF %tT\n", c1, c1); // 2024-07-09 15:11:50
			
			System.out.println(file.isHidden()); // 숨김 파일인가? false
			System.out.println(file.canRead()); // true
			System.out.println(file.canWrite()); // true > false
			System.out.println(file.getParent()); // C:\class\code\java\file
		} else {
			System.out.println("파일이 없습니다.");
		}
		
		
	}
}
