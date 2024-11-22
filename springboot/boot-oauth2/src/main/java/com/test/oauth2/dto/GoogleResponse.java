package com.test.oauth2.dto;

import java.util.Map;

public class GoogleResponse implements OAuth2Response {

	private final Map<String,Object> attribute;
	
	public GoogleResponse(Map<String,Object> attribute) {
		this.attribute = attribute;
	} //구조가 네이버랑 다름
	
	@Override
	public String getProvider() {
		
		return "google";
	}

	@Override
	public String getProviderId() {
		// TODO Auto-generated method stub
		return attribute.get("sub").toString();
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
