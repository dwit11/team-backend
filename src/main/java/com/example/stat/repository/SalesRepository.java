package com.example.stat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.stat.entity.AnalysisSales;
import com.example.stat.view.AnalysisSalesDate;

public interface SalesRepository extends JpaRepository<AnalysisSales, Long> {

	@Query(value = " SELECT id, order_date AS orderDate, " + " sum(number_orders) AS numberOrders, "
			+ " sum(number_items) AS numberItems, " + " sum(product_purchase_amount) AS productPurchaseAmount, "
			+ " sum(delivery_charge) AS deliveryCharge, " + " sum(cash_discount) AS cashDiscount, "
			+ " sum(coupon) AS coupon, "
			+ " sum(product_purchase_amount + delivery_charge - cash_discount - coupon) AS totalPayment, "
			+ " sum(total_refund) AS totalRefund, " + " sum(total_payment - total_refund) AS netSales "
			+ " FROM analysis_sales WHERE DATE(order_date) BETWEEN :fromDate AND :toDate "
			+ " GROUP BY order_date ORDER BY order_date DESC ", nativeQuery = true)

	public List<AnalysisSalesDate> getAnalysisSalesDate(@Param("fromDate") String fromDate,
			@Param("toDate") String toDate);
}
