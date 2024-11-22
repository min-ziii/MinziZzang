package com.test.oauth2.dto;

import java.util.Map;

public class NaverResponse implements OAuth2Response {

	//네이버로부터 받아온 개인정보
	private final Map<String,Object> attribute;
	
	public NaverResponse(Map<String,Object> attribute) {
		this.attribute = (Map<String,Object>) attribute.get("response");
	}
	
	@Override
	public String getProvider() {
		
		
		
		return "naver";
	}

	@Override
	public String getProviderId() {
		// TODO Auto-generated method stub
		return attribute.get("id").toString();
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return attribute.get("email").toString();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return attribute.get("name").toString();
	}

	
	
}
