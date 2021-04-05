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
public class AnalysisProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String dataUrl;
	private String reDate; // 상품 등록일

	private String productCode; // 상품코드
	private String productName; // 상품명
	private long price; // 판매가
	private Integer stock; // 재고
	private Integer paymentQuantity; // 결제수량
	private Integer refundQuantity; // 환불수량
	private Integer salesQuantity; // 판매수량
	private long totalSales; // 판매합계

}
