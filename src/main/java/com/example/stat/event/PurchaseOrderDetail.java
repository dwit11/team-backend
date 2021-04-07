package com.example.stat.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderDetail {
	private long id;
	private Long salesOrderId;
	private long price;
	private long quantity;

	private Product product;
}
