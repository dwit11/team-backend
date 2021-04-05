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
public class AnalysisSales {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String orderDate; // 등록일

	private Integer numberOrders; // 주문수
	private Integer numberItems; // 품목수
	private long productPurchaseAmount; // 상품구매금액
	private long deliveryCharge; // 배송비
	private long cashDiscount; // 할인
	private long coupon; // 쿠폰(금액)
	private long totalPayment; // 결제합계
	private long totalRefund; // 환불합계
	private long netSales; // 순매출

}
