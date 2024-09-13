package com.test.java;

import java.util.HashMap;

public class Test {

	public static void main(String[] args){
		
		HashMap<String,String> hm = new HashMap<>();
		
		hm.put("영어", "불합격");
		hm.remove("영어");
		System.out.println(hm);
		System.out.println(hm.get("영어"));
		
	}

}
