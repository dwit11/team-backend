package com.example.stat.view;

public interface AnalysisSalesDate {
	Long getId();
	String getOrderDate();
	Integer getNumberOrders();
	Integer getNumberItems();
	Long getProductPurchaseAmount();
	Long getDeliveryCharge();
	Long getCashDiscount();
	Long getCoupon();
	Long getTotalPayment();
	Long getTotalRefund();
	Long getNetSales();
}
