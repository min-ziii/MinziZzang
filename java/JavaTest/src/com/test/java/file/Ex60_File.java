package com.test.java.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ex60_File {
	public static void main(String[] args) {
		// Ex60_File.java
		
		/*
	  		1. 파일/디렉토리 조작
	  		- 윈도우 탐색기로 하는 행동을 자바로 구현해보자.
	  		- 파일/디렉토리 정보 열람
	  		- 생성, 수정, 삭제, 이동 등...
	  	
	  		2. 파일 입출력
	  			a. 텍스트 입출력
	  				- 메모장
	  				- Eclipse
	  			
	  			b. 비텍스트 입출력(binary 입출력)
	  				- 동영상
	  				- 이미지
	  				- 음성
	  				
	  			파일 입출력
	  			- java 프로그램 -> 데이터가 꺼져도 계속 저장할 수 있는 -> 보조기억장치(HDD,SSD,USB)같은 쪽으로 이동해야 바람직함
	  			-				->										->											: 쓰기(저장)
	  			-				<-										<-											: 읽기
	  			
	  			저장 장치
	  			- 데이터를 1과 0으로 저장함
	  			- 저장 장치에 저장되는 데이터들은 자료형이 없다.
	  			
	  			
	  			인코딩 Encoding
	  			- 문자 코드 (응용 프로그램의 데이터)를 부호화(0,1로 만듦)하는 작업
	  			- 자바 프로그램의 "홍길동" -> 텍스트 파일 (100100101010)
	  			
	  			디코딩 Decoding
	  			- 부호 데이터를 문자 코드로 변환하는 작업
	  			-텍스트 파일 (100100101010) -> 자바 프로그램의 "홍길동"
	  			
	  			인코딩/디코딩 규칙
	  			- 저장 장치, 네트워크에서 데이터를 표현하는 규칙
	  			
	  			
	  			문자 인코딩 방식
	  			
	  			1. ANSI (미국 국립 표준)
	  			2. UTF (Unicode)
	  				2.1 UTF-8
	  				2.2 UTF-16
	  			3. ISO-8859-1
	  			4. EUC-KR
	  			5. MS949
	  			
	  			ANSI, ISO-8859-1, EUC-KR, MS949
	  			- 영어, 숫자, 특수문자 등: 1byte
	  			- 한글, 비영어권 문자	 : 2byte
	  			
	  			UTF-8
	  			- 영어: 1byte
	  			- 한글: 3byte
	  			
	  			UTF-16
	  			- 영어: 2byte
	  			- 한글: 2byte
	  			
	  			초창기의 문자 수는 256자를 넘지 않았기 때문에 문자 저장에 1바이트를 썼다. -> ASCII 문자코드 체계.
	  			문자 코드값 -> 숫자
	  			문자 하나를 저장하는데 2바이트를 쓰면 Unicode를 지원한다고 말한다.
	  			
	  			
		 */
//		m1();
//		m2();
//		m3();
//		m4();
//		m5();
//		m6();
//		m7();
//		m8();
		m9();
	}// main

	private static void m9() {
		// 성적관리 프로그램.
		// - 성적표 출력
		// - 성적 데이터 파일(score.txt)이 따로 있음
		
		// score.txt 구조 설계
		// -학생명, 국어, 영어, 수학 X n명
		
		File file = new File("dat\\score.txt"); // 절대경로가 너무 기니까 상대경로로 줄임 (project 안에 폴더를 만들었을 때만 가능)
		
		if (file.exists()) {
			System.out.println("==============================================");
			System.out.println("                     성적표");
			System.out.println("==============================================");
			System.out.println("[이름]\t[국어]\t[영어]\t[수학]\t[총점]\t[평균]\t");
			
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line = null;
				
				while((line = reader.readLine()) != null) {
					String[] temp = line.split(",");
					String name = temp[0];
					int kor = Integer.parseInt(temp[1]); 
					int eng = Integer.parseInt(temp[2]); 
					int math = Integer.parseInt(temp[3]);
					int total = kor + eng + math;
					double avg = total / 3.0;
					
					System.out.printf("%s\t%5d\t%5d\t%5d\t%5d\t%5.1f\n", name, kor, eng, math, total, avg);
				}
			} catch (Exception e) {
				System.out.println("Ex60_File.m9");
				e.printStackTrace();
			}
		} else {
			System.out.println("성적 파일이 없습니다.");
		}
	}

	private static void m8() {

		// 콘솔 메모장 (읽기 ver)
		try {
			
			Scanner scan = new Scanner(System.in);
			System.out.print("자바 파일명: "); // Ex01.Java
			String fileName = scan.nextLine();
			String path = "C:\\class\\code\\java\\JavaTest\\src\\com\\test\\java\\" + fileName;
			
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			String line = null;
			int number = 1; // 줄번호
			
			while ((line = reader.readLine()) != null) {
				System.out.printf("%3d: %s\n", number, line);
				number++;
			}
			reader.close();
			
		} catch (Exception e) {
			System.out.println("Ex60_File.m8");
			e.printStackTrace();
		}
		
	}

	private static void m7() {
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("C:\\class\\code\\java\\file\\메모.txt"));
			
			// 한 글자씩 읽고 싶다.
			int code = reader.read(); // 문자 코드 읽기
			System.out.println((char)code);
			
			// 한 문장씩 읽고 싶다.★
			String line = reader.readLine(); // enter를 만날 때까지 한 줄을 읽어옴.
//			System.out.println(line);
//			
//			line = reader.readLine();
//			System.out.println(line);
//			
//			line = reader.readLine();
//			System.out.println(line);
//			
//			line = reader.readLine();
//			System.out.println(line); // 읽을 게 없으면 null 반환
			
			reader.close(); // 기존 stream을 닫고
			
			// 다 읽었는데 처음부터 또 읽고싶다. -> stream을 새로 만든다.
			reader = new BufferedReader(new FileReader("C:\\class\\code\\java\\file\\메모.txt"));
			
			line = null;
			
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			reader.close();
			
		} catch (Exception e) {
			System.out.println("Ex60_File.m7");
			e.printStackTrace();
		}
		
	}

	private static void m6() {

		// FileOutputStream -> FileWriter★ 	-> BufferedWriter★
		// FileInputStream  -> FileReader  	-> BufferedReader★
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\class\\code\\java\\file\\성적.txt"));
			
			writer.write(65);
			writer.write('A');
			writer.write("홍길동");
			writer.newLine(); // writer.write("\r\n");
			writer.write("아무개");
			
			writer.close();
			
			System.out.println("종료");
			
			
		} catch (Exception e) {
			System.out.println("Ex60_File.m6");
			e.printStackTrace();
		}
		
	}

	private static void m5() {

		try {
			
			// FileInputStream -> FileReader
			// 1. 문자 단위 읽기(2byte)
			
			FileReader reader = new FileReader("C:\\class\\code\\java\\file\\메모.txt");
			
			int code = -1;
			while ((code = reader.read()) != -1) {
				System.out.println((char)code);
			}
			reader.close();
			
		} catch (Exception e) {
			System.out.println("Ex60_File.m5");
			e.printStackTrace();
		}
		
	}

	private static void m4() {
		
		// 콘솔 메모장
		try {
			Scanner scan = new Scanner(System.in);
			System.out.print("저장할 파일명: ");
			String filename = scan.nextLine();
			
			FileWriter writer = new FileWriter("C:\\class\\code\\java\\file\\" + filename);
			
			// 내용 입력
			boolean loop = true;
			
			while (loop) {
				System.out.print("입력: ");
				String line = scan.nextLine();
				
				if (line.equals("q!")) { // QUIT!
					break;
				}
				
				writer.write(line); // 한 줄씩 입력 (파일 쓰기)
				writer.write("\r\n");
			}
			writer.close();
			System.out.println("종료");
			
		} catch (Exception e) {
			System.out.println("Ex60_File.m4");
			e.printStackTrace();
		}
		
	}

	private static void m3() {
		//파일 쓰기
		// FileOutputStream은(는) FileWriter(으)로 진화했다!
		// 1. 문자 단위 쓰기 (2byte)
		// 2. 문자열 쓰기
		
		FileWriter writer;
		try {
			
			writer = new FileWriter("C:\\class\\code\\java\\file\\member.txt", true);
			writer.write('가');
			writer.write('나');
			writer.write('다');
			writer.write("\r\n");
			writer.write("홍길동");
			writer.close();
			System.out.println("종료");
			
		} catch (IOException e) {
			System.out.println("Ex60_File.m3");
			e.printStackTrace();
		}
		
		
	}

	private static void m2() {
		
		try {
			 // 파일 읽기 (입력)
			FileInputStream stream = new FileInputStream("C:\\class\\code\\java\\file\\text.txt");
			
			// 이 logic을 꼭 기억할 것.★
			int code = -1;
			while ((code = stream.read()) != -1) { // 읽은 글자가 있니?
				System.out.print((char)code);
			}
						
			stream.close();
			
//			int c = stream.read();
//			System.out.print((char)c);
//			
//			c = stream.read();
//			System.out.print((char)c);
//
//			c = stream.read();
//			System.out.print((char)c);
//			
//			c = stream.read();
//			System.out.print((char)c);
//			
//			c = stream.read();
//			System.out.print((char)c);
//			
//			c = stream.read();
//			System.out.print((char)c);
//			
//			c = stream.read();
//			System.out.print((char)c);
//			c = stream.read();
//			System.out.print((char)c);
//			c = stream.read();
//			System.out.print((char)c);
//			c = stream.read();
//			System.out.print((char)c);
//			c = stream.read();
//			System.out.print((char)c);
//			c = stream.read();
//			System.out.print((char)c);
//			c = stream.read();
//			System.out.println(c);
			
			
			
			
		} catch (Exception e) {
			System.out.println("Ex60_File.m2");
			e.printStackTrace();
		}
		
	}

	private static void m1() {
		
		// 파일 쓰기(출력)
		
		try {
			File file = new File("C:\\class\\code\\java\\file\\text.txt");
			
			// 파일 객체 -> 출력 스트림 생성
			// 출력 스트림 모드
			// 1. create mode <- 기본값. (새로 쓰기)
			// FileOutputStream stream = new FileOutputStream(file);
			// 	a. 파일이 없으면 자동 생성
			// 	b. 파일이 있으면 덮어쓰기
			// 2. append mode (이어쓰기)
			//  a. 파일이 없으면 자동 생성
			// 	b. 파일이 있으면 이어쓰기
			FileOutputStream stream = new FileOutputStream(file, true); // stream 열기
//			char c = 'N';
//			stream.write((int)c); // test.txt에 'A'를 적어라
//			stream.write(c);
//			stream.write('M');
//			stream.write('O');
			
			String line = "Hello World~";
			
			for (int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);
				stream.write(c);
			}
			stream.close(); // stream 닫기
			System.out.println("종료");
			
		} catch (Exception e) {
			System.out.println("Ex60_File.m1");
			e.printStackTrace();
		}
		
	}
}// class
