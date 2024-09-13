package com.test.question;

import java.util.Scanner;

public class Q013 {
	public static void main(String[] args) {
		// 국어, 영어, 수학 점수를 전달하면 '합격' 혹은 '불합격'이라는 단어를 반환하는 메소드를 선언하시오.
		// 조건1: String test(int kor, int eng, int math) method를 완성하라.
		// 조건2: 평균 점수 60점 이상은 합격이고, 60점 미만은 불합격이다.
		// 조건3: 한 과목 이상이 40점 미만이면 과락으로 불합격 처리된다.
		
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("국어: ");
		int kor = scan.nextInt();
		
		System.out.print("영어: ");
		int eng = scan.nextInt();
		
		System.out.print("수학: ");
		int math = scan.nextInt();
		
		System.out.printf("%s입니다.", test(kor, eng, math));
		
		scan.close();
		
	}
	
	public static String test(int kor, int eng, int math) {
		int avg = (kor + eng + math) / 3;
		String result = kor < 40 ? "불합격" : eng < 40 ? "불합격" : math < 40 ? "불합격" : avg < 60 ? "불합격" : "합격";
		
		return result;
	}
}
