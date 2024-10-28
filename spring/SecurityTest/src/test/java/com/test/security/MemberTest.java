package com.test.security;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
			"file:src/main/webapp/WEB-INF/spring/root-context.xml",
			"file:src/main/webapp/WEB-INF/spring/security-context.xml"
		})
public class MemberTest {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void testNotNull() {
		assertNotNull(dataSource);
		assertNotNull(passwordEncoder);
	}
	
	@Test
	public void testEncrypt() {
		
		//$2a$10$juIiFGn878lJu2Ko57sguOMr9aW1CLwxQUr9vMnbAycIN/mfLr88S
		//$2a$10$ZGedZCKrCB49P08qQBvsFepia4x8QE6u43qLJdM2hZdzhrNZ9SI86
		
		String pw = "1111";
		System.out.println(passwordEncoder.encode(pw));
		
		String pw2 = "1111";
		System.out.println(passwordEncoder.encode(pw2));
		
	}
	
	@Test
	@Ignore //여기로!!  member
	public void insertMember() {
		
		String sql = "insert into member (memberid, memberpw, membername, enabled, email, age, gender) values (?, ?, ?, default, ?, ?, ?)";
		
		try {
			
			Connection conn = dataSource.getConnection();
			PreparedStatement stat = conn.prepareStatement(sql);
			
			stat.setString(1, "dog");
			stat.setString(2, passwordEncoder.encode("1111"));
			stat.setString(3, "강아지");
			stat.setString(4, "dog@gmail.com");
			stat.setString(5, "3");
			stat.setString(6, "m");
			stat.executeUpdate();
			
			stat.setString(1, "cat");
			stat.setString(2, passwordEncoder.encode("1111"));
			stat.setString(3, "고양이");
			stat.setString(4, "cat@gmail.com");
			stat.setString(5, "2");
			stat.setString(6, "f");
			stat.executeUpdate();
			
			stat.setString(1, "tiger");
			stat.setString(2, passwordEncoder.encode("1111"));
			stat.setString(3, "호랑이");
			stat.setString(4, "tiger@gmail.com");
			stat.setString(5, "10");
			stat.setString(6, "f");
			stat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test //여기로 인증 허가 member_auth
	public void insertAuth() {
		
		String sql = "insert into member_auth (memberid, auth) values (?, ?)";
		
		try {
			
			Connection conn = dataSource.getConnection();
			PreparedStatement stat = conn.prepareStatement(sql);
			
			stat.setString(1, "dog");
			stat.setString(2, "ROLE_MEMBER");
			stat.executeUpdate();
			
			stat.setString(1, "cat");
			stat.setString(2, "ROLE_MEMBER");
			stat.executeUpdate();
			
			stat.setString(1, "tiger");
			stat.setString(2, "ROLE_MEMBER");
			stat.executeUpdate();
			
			stat.setString(1, "tiger");
			stat.setString(2, "ROLE_ADMIN");
			stat.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}










