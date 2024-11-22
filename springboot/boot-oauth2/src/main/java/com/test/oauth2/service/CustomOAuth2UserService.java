package com.test.oauth2.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.test.oauth2.dto.CustomOAuth2User;
import com.test.oauth2.dto.GoogleResponse;
import com.test.oauth2.dto.NaverResponse;
import com.test.oauth2.dto.OAuth2Response;
import com.test.oauth2.dto.UserDTO;
import com.test.oauth2.entity.UserEntity;
import com.test.oauth2.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService{

	private final UserRepository userRepository;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		System.out.println("네이버/구글로부터 받아온 개인 정보>>>" + oAuth2User);
		
		//네이버 or 구글 확인 > naver or google
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		
		/*
		 
		 	네이버/구글 > 개인 정보 응답 > OAuth2Response
		 	
		 	네이버 데이터
		 	{
		 		resultcode=00,
		 		message=success,
		 		response={
		 			id = 1111,
		 			name = 홍길동
		 		}
		 	}
		 
		 	구글 데이터
		 	{
		 		resultcode=00,
		 		message=success,
		 		id=1111,
		 		name=홍길동
		 	}
		 
		 */
		
		OAuth2Response oAuth2Response = null;
		
		if (registrationId.equals("naver")) {
			oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
		} else if (registrationId.equals("google")) {
			oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
		} else {
			return null;
		}
		
		//유저 식별자 중복 문제
		String username = oAuth2Response.getProviderId() + ""+ oAuth2Response.getProviderId();
		
		System.out.println("우리 사이트에서 사용할 ID: " + username);
		
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(username);
		userDTO.setName(oAuth2Response.getName());
		userDTO.setRole("ROLE_MEMBER");
		
		//DB저장
		//- 첫방문 or 재방문
		
		UserEntity user = userRepository.findByUsername(username);
		
		if (user == null) {
			
			UserEntity entity = UserEntity.builder()
									.username(username)
									.email(oAuth2Response.getEmail())
									.role("ROLE_MEMBER")
									.build();
			userRepository.save(entity);
			
		} else {
			
		}
		
		return new CustomOAuth2User(userDTO); //인증 객체
	}
	
	

}
