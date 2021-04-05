package com.example.stat.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartProduct {
	private long id;

	private Product product;
	private Integer price;
	private Integer quantily;
}
