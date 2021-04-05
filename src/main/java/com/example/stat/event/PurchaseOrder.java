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
	private String phone;
	private String payment;
	private Integer amount;
	private String note;
	private String orderDate;

	private List<PurchaseOrderProduct> orderProducts;
}
