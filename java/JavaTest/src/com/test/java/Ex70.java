package com.test.java;

import com.github.lalyos.jfiglet.FigletFont;

public class Ex70 {
	public static void main(String[] args) {
		//figlet
		
		try {
			String asciiArt1 = FigletFont.convertOneLine("Tennis Score!");
		    System.out.println(asciiArt1);
		    
		    String asciiArt2 = FigletFont.convertOneLine(FigletFont.class.getResourceAsStream("big.flf"), "hello");
		    System.out.println(asciiArt2);
		    
		} catch (Exception e) {
			System.out.println("Ex70.main");
			e.printStackTrace();
		}
		
	}
}
