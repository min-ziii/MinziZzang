package com.test.security.mapper;

import org.apache.ibatis.annotations.Select;

public interface SecurityMapper {

	@Select("select sysdate from dual")
	String time1();
	
	String time2();
	
}





