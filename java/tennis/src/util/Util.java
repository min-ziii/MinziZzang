package util;

import java.io.BufferedReader;
import java.io.FileReader;

public class Util {
    public static String getCurrentId(String path) {
    	String result = "1";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));

            String line;
            String lastLine = "";

            // 파일의 마지막 라인까지 읽음
            while ((line = reader.readLine()) != null) {
                lastLine = line;
            }

            reader.close();

            // 파일이 비었으면 1번부터 시작
            if(lastLine.length() == 0) {
            	return result;
            }

            // 마지막 아이디를 1 증가
            result = String.valueOf(Integer.parseInt(lastLine.split(",")[0]) + 1 );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void printTitle(String title) {
        int lineLength = 40;

        System.out.println();
        System.out.println(getLineString(lineLength));
        System.out.println(getCenteredString(title, lineLength));
        System.out.println(getLineString(lineLength));
    }

    private static int getStringLength(String str) {
        int length = 0;
        for (int i = 0; i < str.length(); i++) {
            if (isKorean(str.charAt(i))) {
                length += 2; // 한글 문자 -> 2 길이
            } else {
                length += 1; // 영문/숫자 문자 -> 1 길이
            }
        }
        return length;
    }

    private static boolean isKorean(char c) {
        return (c >= '\u3131' && c <= '\u3163') || (c >= '\uAC00' && c <= '\uD7A3');
    }

    private static String getLineString(int length) {
        return "=".repeat(length);
    }

    private static String getCenteredString(String str, int length) {
        int strLength = getStringLength(str);
        int paddingLength = (length - strLength) / 2;
        return " ".repeat(paddingLength) + str + " ".repeat(paddingLength);
    }
}