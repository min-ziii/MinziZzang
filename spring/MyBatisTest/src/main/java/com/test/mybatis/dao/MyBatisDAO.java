package com.test.mybatis.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.mybatis.dto.AddressDTO;

@Repository
public class MyBatisDAO {
	
	//Connection > Statement
	//SqlSessionTemplate
	
	
	//DAO > (의존) > SqlSessionTemplate
	@Autowired
	private SqlSessionTemplate template;

	public void m1() {
		
		/*
		 	JDBC Statment
		 	1. excuteUpdate()
		 		- template.insert()
		 		- template.update()
		 		- template.delete()
		 	
		 	2. excuteQuery()
		 		- template.selectOne() > 결과셋의 레코드 1줄
		 		- template.selectList() > 결과셋의 레코드 N줄
		 	
		 
		 */
		
		template.insert("mybatis.m1");
		
		
		
		
	}

	public int m2(String seq) {
		
		
		return template.delete("mybatis.m2", seq);
		
		
	}

	public int m3(AddressDTO dto) {
		
		return template.update("mybatis.m3", dto);
	}

	
	

}





















