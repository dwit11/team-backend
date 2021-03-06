package com.example.stat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.stat.entity.AnalysisCart;
import com.example.stat.view.AnalysisCartQuantity;
import com.example.stat.view.AnalysisCartStock;

public interface CartRepository extends JpaRepository<AnalysisCart, Long> {
	@Query(value = " SELECT id, product_name AS productName, " + " sum(quantity) AS quantity, stock "
			+ " FROM analysis_cart " + " GROUP BY product_code " + " ORDER BY quantity DESC limit 10 " , nativeQuery = true)

	public List<AnalysisCartStock> getAnalysisCartStock();

	@Query(value = " SELECT id, product_name AS productName, sum(quantity) AS quantity " + " FROM analysis_cart"
			+ " GROUP BY product_code" + " ORDER BY quantity " + " DESC limit 10 ", nativeQuery = true)

	public List<AnalysisCartQuantity> getAnalysisCartQuantity();

	@Query(value = " SELECT id, product_code, price, product_name, sum(quantity) AS quantity, stock, "
			+ " sum(number_members) AS number_members, sum(number_non_members) AS number_non_members "
			+ " FROM analysis_cart " + " GROUP BY product_code " + " ORDER BY quantity DESC", nativeQuery = true)

	public List<AnalysisCart> getAnalysisCart();

}
