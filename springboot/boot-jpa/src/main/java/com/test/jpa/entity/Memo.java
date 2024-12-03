package com.test.jpa.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter //select 날리려면 필수
@ToString
@Table(name = "tblMemo")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Memo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "memo_seq_generator")
	@SequenceGenerator(name="memo_seq_generator", sequenceName = "seqMemo", allocationSize = 1)
	
	private Long seq;
	
	
	private String memo;
	private LocalDate regdate;
	
	@Column(insertable = false, updatable = false)
	private Long aseq;
	
	//자식 > 부모 참조
	@ManyToOne
	@JoinColumn(name = "aseq")
	private Address address;

}
