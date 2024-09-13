package com.test.question;

public class Q095 {
	public static void main(String[] args) {
		
		Employee e1 = new Employee();

		e1.setName("홍길동");
		e1.setDepartment("홍보부");
		e1.setPosition("부장");
		e1.setTel("010-1234-5678");
		e1.setBoss(null); //직속 상사 없음

		e1.info();


		Employee e2 = new Employee();

		e2.setName("아무개");
		e2.setDepartment("홍보부");
		e2.setPosition("사원");
		e2.setTel("010-2541-8569");
		e2.setBoss(e1); //직속 상사 e1(홍길동)

		e2.info();
	}
}

class Employee {
	   private String name;
	   private String department;
	   private String position;
	   private String tel;
	   private Employee boss;

	   //getter, setter 구현

	public String getName() {
		return name;
	}

	public void setName(String name) { // 2~5자 이내
		if (name.length() < 2 || name.length() > 5) {
			return;
		}
		if (name.charAt(0) < '가' || name.charAt(0) > '힣') { // 이름이 한글일 것
			return;
		}
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		switch (department) {
			case "영업부":
				this.department = department;
				break;
			case "기획부":
				this.department = department;
				break;
			case "총무부":
				this.department = department;
				break;
			case "개발부":
				this.department = department;
				break;
			case "홍보부":
				this.department = department;
				break;
			default:
				return;
		}
		
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) { // 직책
		switch (position) {
		case "부장":
			this.position = position;
			break;
		case "과장":
			this.position = position;
			break;
		case "대리":
			this.position = position;
			break;
		case "사원":
			this.position = position;
			break;
		default:
			return;
		}
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) { // 010-XXXX-XXXX 형식 확인
		if (tel.contains("010-")) {
			this.tel = tel;
		} else return;
		
	}

	public Employee getBoss() {
		return boss;
	}

	public void setBoss(Employee boss) {
		if (this.department != boss.department) {
			return;
		} else {
			this.boss = boss;
		}
	}

	public void info() {
		if (boss == null) {
//			boss = "없음";
		}
		System.out.printf("[%s]\n"
						+ "- 부서: %s"
						+ "- 직위: %s"
						+ "- 연락처: %s"
						+ "- 직속상사: %s(%s %s)",
						name, department, position, tel, boss.name, boss.department, boss.position);
	   }
	}