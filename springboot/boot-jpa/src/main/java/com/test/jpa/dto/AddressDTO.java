package com.test.jpa.dto;

import com.test.jpa.entity.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
	
	private Long seq;
	private String name;
	private Integer age;
	private String address;
	private String gender;
	
	private Integer birthYear;
	
	//m26에 2개의 인자 받는 코드 만들어주기
	public AddressDTO(String name, String address) {
		this.name = name;
		this.address = address;
	}
	
	//본인(DTO) > (변환) > 엔티티
	public static Address toEntity(AddressDTO dto) {
		
		return Address.builder()
					.seq(dto.getSeq())
					.name(dto.getName())
					.age(dto.getAge())
					.address(dto.getAddress())
					.gender(dto.getGender())
					.build();
		
	}
	
	
	public Address toEntity() {
		
		return Address.builder()
					.seq(this.getSeq())
					.name(this.getName())
					.age(this.getAge())
					.address(this.getAddress())
					.gender(this.getGender())
					.build();
		
	}
	
	

}
