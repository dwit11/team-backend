package com.example.stat.event;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrder {
	private long id;

	private String name;
	private String address;
	private String tel;
	private long pmt;
	private String orderDate;
	private String pay; // 결제 방법
	private String note; // 요청사항

	private List<PurchaseOrderDetail> salesOrderDetail;
}
