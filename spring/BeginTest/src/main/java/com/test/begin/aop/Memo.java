package com.test.begin.aop;

public interface Memo {
	
	void add(String memo);
	void addSecret(String memo);
	String read(int seq) throws Exception;
	boolean edit(int seq, String memo);
	boolean del(int seq);

}
