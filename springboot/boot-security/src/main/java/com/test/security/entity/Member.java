package com.test.security.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@Builder
//오라클에 있는 테이블 명이 Member라서 필요 없음@Table(name = "tblMember")
@AllArgsConstructor
@NoArgsConstructor
public class Member {

	@Id //primary key 지정
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
	@SequenceGenerator(name="member_seq_generator", sequenceName = "seqMember", allocationSize = 1)
	private Long seq;
	
	@Column(unique = true, nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String role;
	
	
	
}


















