package com.test.java.file;

// 데이터 전달용 포장상자 Class
// DTO를 만들 때는 private 멤버 변수 + Getter + Setter만 구현하는 게 국룰임
// 가끔 debug용으로 toString()을 만들어 놓는 경우도 있긴 함
public class ScoreDTO {
	
	private String name;
	private int kor;
	private int eng;
	private int math;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	
	@Override
	public String toString() {
		return "ScoreDTO [name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math + "]";
	}
}
