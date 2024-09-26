package com.test.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class Ex05_PreparedStatement {
	
	public static void main(String[] args) {
		
		//Ex05_PreparedStatement.java
		
		//PreparedStatement
		//- 매개변수를 지원
		//- Statement > 정적 SQL
		//- PreparedStatement > 동적 SQL
		
		
		//UI + SQL + 데이터 사용자 입력
				Scanner scan = new Scanner(System.in);
				
				//*** 자바와 오라클은 아무 관계가 없다.
				//- 자바는 오라클의 자료형이 뭔지 모른다.
				System.out.print("이름: ");
				String name = scan.nextLine();
				
				System.out.print("나이: ");
				String age = scan.nextLine();
				
				System.out.print("성별(m,f): ");
				String gender = scan.nextLine();
				
				System.out.print("전화번호: ");
				String tel = scan.nextLine();
				
				System.out.print("주소: ");
				String address = scan.nextLine();
				//address = address.replace("'", "''");
				
				//String sql = String.format("insert into tblAddress (seq, name, age, gender, tel, address, regdate) values (seqAddress.nextVal, '%s', %s, '%s', '%s', '%s', default)", name, age, gender, tel, address);
				
				String sql = "insert into tblAddress (seq, name, age, gender, tel, address, regdate) values (seqAddress.nextVal, ?, ?, ?, ?, ?, default)";
				
				Connection conn = null;
				Statement stat = null;
				PreparedStatement pstat = null;
				
				try {
					
					conn = DBUtil.open();			
					//stat = conn.createStatement();
					pstat = conn.prepareStatement(sql);
					
					pstat.setString(1,  name);
					pstat.setString(2, age);
					pstat.setString(3, gender);
					pstat.setString(4, tel);
					pstat.setString(5, address);
					
					
					//System.out.println(stat.executeUpdate(sql));
					System.out.println(pstat.executeUpdate(sql));
					
					stat.close();
					conn.close();
					
				} catch (Exception e) {
					System.out.println("Ex03_Statement.m5");
					e.printStackTrace();
				}
				

	}

}
