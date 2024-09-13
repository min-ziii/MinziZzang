package com.test.question;

import java.util.Scanner;

public class Q062 {
	public static void main(String[] args) {
		// �л��� �̸��� N�� �Է¹޾� �Ʒ��� ���� ����Ͻÿ�.
		// �̸��� �������� �����Ͻÿ�.
		
		
		Scanner scan = new Scanner(System.in);		

		System.out.print("학생 수: ");
		
		String[] name = new String[scan.nextInt()];
		scan.nextLine(); // buffer���� enter ���ڸ� ������
		
		for (int i=0; i < name.length; i++) {
			System.out.print("학생명: ");
			name[i] = scan.nextLine();
		}
		
		// �䱸����) �̸��� ������������ �����Ͻÿ�. -> cresendo
		// compareTo() Method
		
		// 1. �������� ��
		//  - ���ذ��� �񱳴���� ������ 0
		//  - ���ذ��� �񱳴�󺸴� ������ -1
		//  - ���ذ��� �񱳴�󺸴� ũ�� 1
		
		// 2. �������� ��
		// 2-1. �񱳴�� ���� ���ڿ��� ����
		// 	- �� ���ڿ� ������ ���̰��� return
		//  - ���ذ��� �񱳴��� ���̰� ������ 0
		//  - ���ذ��� �񱳴�󺸴� ª���� ����
		//	- ���ذ��� �񱳴�󺸴� ��� ���
		// 	- ù��° ���ں��� �ٷ� �ٸ��� �� ���ڿ� �� �񱳰� �Ұ����� ������ ���ڿ� ASCII���� �������� ���̰� ���
		// 2-2. �񱳴���� ���� �ٸ� ���ڿ�
		//  - �� ���ڿ� �� �񱳰� �Ұ����� ������ ���ڿ� ASCII���� �������� ���̰� ���
		
		String temp = "";
		
		for (int i = 0; i < name.length; i++) {
			for (int j=0; j < name.length-i-1; j++) {
				if (name[j].compareTo(name[j+1]) > 0) { // ���ʹ��ڿ��� �����ʺ��� ũ��
					temp = name[j];
					name[j] = name[j+1];
					name[j+1] = temp;
				}
			}
		}
		
		
		System.out.printf("입력한 학생은 총 %d명입니다.\n", name.length);
		
		for (int j=0; j < name.length; j++) {
			System.out.printf("%d. %s\n", j+1, name[j]);
		}
		
		scan.close();
		
	}
}
