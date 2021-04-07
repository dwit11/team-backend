package com.example.stat.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
	private long id;

	private String userId;
	private String amount;
	private Product product;

}
