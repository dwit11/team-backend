package com.example.stat.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AnalysisCart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String productCode; // 상품코드
	private String productName; // 상품명
	private long price; // 판매가
	private Integer quantity; // 수량
	private Integer stock; // 재고
	private Integer numberMembers; // 회원수
	private Integer numberNonMembers; // 비회원수
}
