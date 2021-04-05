package com.example.stat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.stat.entity.AnalysisProduct;
import com.example.stat.view.AnalysisProductDate;
import com.example.stat.view.AnalysisProductSalesQuantity;
import com.example.stat.view.AnalysisProductTotalSales;

public interface ProductRepository extends JpaRepository<AnalysisProduct, Long> {
	// 해당 기간 조회

	@Query(value = " SELECT id, product_code AS productCode, product_name AS productName, price,"
			+ " sum(payment_quantity) AS paymentQuantity, sum(refund_quantity) AS refundQuantity , "
			+ " sum(payment_quantity - refund_quantity) AS salesQuantity,  "
			+ " price * (sum(payment_quantity - refund_quantity)) AS totalSales " + " FROM analysis_product "
			+ " WHERE DATE(re_date) BETWEEN :fromDate AND :toDate " + " GROUP BY product_code "
			+ " ORDER BY salesQuantity DESC ", nativeQuery = true)

	public List<AnalysisProductDate> AnalysisProductDate(@Param("fromDate") String fromDate,
			@Param("toDate") String toDate);

	// 해당 기간 TOP10 상품 (판매 수량)

	@Query(value = " SELECT id, product_name AS productName, "
			+ " sum(payment_quantity - refund_quantity) AS salesQuantity " + " FROM analysis_product "
			+ " WHERE DATE(re_date) BETWEEN :fromDate AND :toDate " + " GROUP BY product_code "
			+ " ORDER BY salesQuantity DESC limit 10", nativeQuery = true)

	public List<AnalysisProductSalesQuantity> getAnalysisProductSalesQuantity(@Param("fromDate") String fromDate,
			@Param("toDate") String toDate);

	// 해당 기간 TOP10 상품 (판매 합계)

	@Query(value = " SELECT id, product_name AS productName, "
			+ " price * (sum(payment_quantity - refund_quantity)) AS totalSales " + " FROM analysis_product  "
			+ " WHERE DATE(re_date) BETWEEN :fromDate AND :toDate " + " GROUP BY product_code "
			+ " ORDER BY total_Sales DESC limit 10 ", nativeQuery = true)

	public List<AnalysisProductTotalSales> getAnalysisProductTotalSales(@Param("fromDate") String fromDate,
			@Param("toDate") String toDate);
}
