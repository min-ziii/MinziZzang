package com.test.java.file;

import java.util.Scanner;

// Ex61 업무 구현 Class
public class ScoreService {

	private Scanner scan;
	private ScoreDAO dao;
	private ScoreView view;
	
	public ScoreService() { // Constructor
		this.scan = new Scanner(System.in);
		this.dao = new ScoreDAO();
		this.view = new ScoreView();
	}

	public void add() {
		
		String name = "";
		int kor = 0;
		int eng = 0;
		int math = 0;
		
		// 성적 추가
		view.subTitle("성적추가");
		
		System.out.print("이름: ");
		name = scan.nextLine();
		
		System.out.print("국어: ");
		kor = scan.nextInt();
		
		System.out.print("영어: ");
		eng = scan.nextInt();
		
		System.out.print("수학: ");
		math = scan.nextInt();
		scan.skip("\r\n");
		
		// 데이터 집합
		// 1. 배열
		// 2. 컬렉션
		// 3. 클래스
		
		// 인자 개수가 2개를 넘어가면 직접 전달하지 않는 편. 관리가 힘듦.
		// -> 데이터 집합에 담아서 상자를 넘긴다.
		// -> Class는 서로 다른 타입의 멤버 변수를 다 넣을 수 있다.
		
		// 상자 역할 클래스로 자주 쓰는 이름은?
		// - ScoreDTO(Data Transfer Object)
		// - ScoreVO(Value Object)
		// - ScoreEntity
		
		ScoreDTO dto = new ScoreDTO();
		
		dto.setName(name);
		dto.setKor(kor);
		dto.setEng(eng);
		dto.setMath(math);
		//dao.add(name, kor, eng, math);
		dao.add(dto);
		
		System.out.println("성적 추가 완료");
		
		//잠시 멈춤
		view.pause();
		
	}

	public void get() {
		// 특정 학생의 성적 열람
		
		view.subTitle("성적확인");
		
		System.out.print("학생명: ");
		String name = scan.nextLine();
		
		ScoreDTO dto = dao.get(name); // dao는 데이터를 주고, dto는 데이터를 받아서 포장. 출력은 다른 놈이 해.
		
		if (dto != null) {
			System.out.printf("이름: %s\n", dto.getName());
			System.out.printf("국어: %d점\n", dto.getKor());
			System.out.printf("영어: %d점\n", dto.getEng());
			System.out.printf("수학: %d점\n", dto.getMath());
		} else {
			System.out.println("학생이 없습니다.");
		}
		
		view.pause();
		
	}

	public void edit() {
		// 특정 학생의 성적 수정
		view.subTitle("성적수정");
		
		System.out.print("학생명: ");
		String name = scan.nextLine();
		
		ScoreDTO dto = dao.get(name);
		
		if (dto != null) {
			System.out.println(dto);
			
			System.out.printf("국어: %d점\n", dto.getKor());
			System.out.printf("수정: ");
			int kor = scan.nextInt();
			if (kor != -1) {
				dto.setKor(kor); // 새로 받은 점수로 수정
			}
			
			System.out.printf("영어: %d점\n", dto.getEng());
			System.out.printf("수정: ");
			int eng = scan.nextInt();
			if (eng != -1) {
				dto.setEng(eng); // 새로 받은 점수로 수정
			}
			
			System.out.printf("수학: %d점\n", dto.getMath());
			System.out.printf("수정: ");
			int math = scan.nextInt();
			if (math != -1) {
				dto.setMath(math); // 새로 받은 점수로 수정
			}
			
			System.out.println(dto);
			
			dao.edit(dto);
			
		} else {
			System.out.println("학생이 없습니다.");
		}
		
		view.pause();
		
	}

	public void remove() {
		// 특정 학생의 성적 삭제(수정)
		view.subTitle("성적수정");
		
		System.out.print("학생명: ");
		String name = scan.nextLine();
		
		ScoreDTO dto = dao.get(name);
		
		if (dto != null) {
			// 성적 삭제
			dao.remove(dto);
		} else {
			System.out.println("학생이 없습니다.");
		}
		
		view.pause();
	}
	
}
