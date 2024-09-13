package com.test.java;

public class Ex04_Variable_Example {

	public static void main(String[] args) {
		// 9개의 자료형 각각 변수를 하나 생성하고 값을 대입하여 문장으로 출력하는 작업을 10번 반복하라.
		// 자료형은 byte, short, int, long, float, double, boolean, character, String이 있다.
		
		byte minNum = 1;
		System.out.println("가장 작은 양의 정수는 " + minNum + "입니다. 우리가 태어나서 가장 처음으로 배우는 숫자일 겁니다.");
		
		short score = 100;
		System.out.println("어렸을 때, 부모님을 기쁘게 해드리는 방법 중 하나는 시험에서 " + score + "점을 맞는 것이었습니다.");
		
		int iLoveU3000 = 3000;
		System.out.println("'크다' 라는 개념에 대한 표현을 할 때도, 우리가 알고 있는 큰 숫자를 가져와 얘기하곤 했죠. " + iLoveU3000 + "만큼 사랑한다는 말 아시잖아요?");
		
		long maxNum = Long.MAX_VALUE;
		System.out.println("코딩 공부를 하고 계시니 살짝 알려드리는 건데, long 타입 자료형이 나타낼 수 있는 가장 큰 수는 " + maxNum + "이랍니다. 외울 필요는 없어요.");
		
		final float PI = 3.14F;
		System.out.println(PI + "같이 원주율 값처럼 변할 일이 없는 상수를 변수에 넣어 쓸 수 있는 방법도 있다구요.");
		
		double highestTemperatureDaily = 34.3;
		System.out.println("정말 덥네요. 오늘 최고 기온이 " + highestTemperatureDaily + "도라는 거 있죠. 물 한 잔 하고 마지막 팁도 알려드릴게요.");
		
		boolean answerYes = true;
		boolean answerNo = false;
		System.out.println("boolean형 변수가 가질 수 있는 값은 " + answerYes + "와 " + answerNo + " 뿐입니다.");
		
		long annualBudget = 1000000000000000L;
		System.out.println("미국은 매년 국방 예산이 " + annualBudget + "원을 넘기는 나라라고 해서 천조국이라는 별명이 붙었습니다.");
		
		char monetaryUnit01 = '만';
		char monetaryUnit02 = '억';
		char monetaryUnit03 = '조';
		System.out.println(monetaryUnit03 + "단위는 애초에 바라지도 않지만, " + monetaryUnit02 + " 단위의 연봉을 받기 위해선 얼마나 실력이 좋아야 할까요?");
		
		String manName = "영진";
		System.out.println(manName + "씨가 충분히 그런 사람이 될 수 있다고 저는 믿습니다. 열심히 해봐요.");
		

	}

}
