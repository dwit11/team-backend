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
public class Product {
	private long id;

	private String name;
	private String productCode;
	private String description;
	private String category;
	private String reDate;
	private Integer price;
	private long stock;

	private List<ProductFile> files;
}
