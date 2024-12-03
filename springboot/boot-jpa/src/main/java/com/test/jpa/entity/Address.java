package com.test.jpa.entity;

import java.util.List;

import com.test.jpa.dto.AddressDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

//Entity
//- 데이터베이스의 테이블의 참조 객체
//- DB의 tblAddress 테이블과 연결된 객체
//- 엔티티에는 Setter를 구현 안함 > 생성자로 구현
//- 엔티티에는 필요에 따라 Getter는 구현 가능함.
@Entity
@Getter
@ToString
@Table(name = "tblAddress") //테이블 매핑
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq_generator")
	@SequenceGenerator(name="address_seq_generator", sequenceName = "seqAddress", allocationSize = 1)
	private Long seq;
	
	private String name;
	private Integer age;
	private String address;
	private String gender;	
	
	
	//Entity(본인) > (변환) > DTO
	public static AddressDTO toDTO(Address address) {
		
//		AddressDTO dto = new AddressDTO();
//		dto.setSeq(address.getSeq());
//		dto.setName(address.getName());
//		dto.setAge(address.getAge());
//		dto.setAddress(address.getAddress());
//		dto.setGender(address.getGender());
		
		return AddressDTO.builder()
					.seq(address.seq)
					.name(address.name)
					.age(address.age)
					.address(address.address)
					.gender(address.gender)
					.build();
	}
	
	public AddressDTO toDTO() {
		
//		AddressDTO dto = new AddressDTO();
//		dto.setSeq(this.getSeq());
//		dto.setName(this.getName());
//		dto.setAge(this.getAge());
//		dto.setAddress(this.getAddress());
//		dto.setGender(this.getGender());
		
		return AddressDTO.builder()
				.seq(this.getSeq())
				.name(this.getName())
				.age(this.getAge())
				.address(this.getAddress())
				.gender(this.getGender())
				.build();		
	}
	
	// *** JPA의 Entity는 MVC에서 흔히 사용하는 DTO는 역할이 다르다.
	//- DTO > 계층간의 데이터 전달
	//- setName() 호출 > 전달할 데이터 추가 or 변경
	//- Entity > DB 조작
	
	//주소 수정 Setter
	public void updateAddress(String address) {
		this.address = address;
	}
	
//	@ManyToOne
//	@JoinColumn(name = "seq")
//	private Info info;
	
	@OneToOne(mappedBy = "address")
    private Info info;
	
	//1:N
	@OneToMany
	@JoinColumn(name = "aseq")
	private List<Memo> memo;
	
}







