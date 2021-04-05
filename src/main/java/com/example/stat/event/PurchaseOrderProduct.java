package com.example.stat.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderProduct {
	private long id;

	private Product product;
	private Integer amt;
	private Integer quantity;
	private Integer cashDiscount;

}
