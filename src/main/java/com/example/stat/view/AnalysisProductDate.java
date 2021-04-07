package com.example.stat.view;

public interface AnalysisProductDate {
	Long getId();

	String getProductCode();

	String getProductName();

	String getPrice();

	Integer getPaymentQuantity();

	Integer getRefundQuantity();

	Integer getSalesQuantity();

	Long getTotalSales();
}
