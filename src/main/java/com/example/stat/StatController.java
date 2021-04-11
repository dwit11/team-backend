package com.example.stat;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

import com.example.stat.entity.AnalysisCart;
import com.example.stat.repository.CartRepository;
import com.example.stat.repository.ProductRepository;
import com.example.stat.repository.SalesRepository;
import com.example.stat.view.AnalysisCartQuantity;
import com.example.stat.view.AnalysisCartStock;
import com.example.stat.view.AnalysisProductDate;
import com.example.stat.view.AnalysisProductSalesQuantity;
import com.example.stat.view.AnalysisProductTotalSales;
import com.example.stat.view.AnalysisSalesDate;

@RestController
public class StatController {
	private CartRepository cartRepo;
	private ProductRepository productRepo;
	private SalesRepository salesRepo;

	public StatController(CartRepository cartRepo, ProductRepository productRepo, SalesRepository salesRepo) {
		this.cartRepo = cartRepo;
		this.productRepo = productRepo;
		this.salesRepo = salesRepo;

	}

	// 장바구니 통계/분석
	@GetMapping("/cart/search")
	public List<AnalysisCart> getAnalysisCart() {
		return cartRepo.getAnalysisCart();
	}

	@GetMapping("/cart/top10/stock")
	public List<AnalysisCartStock> getAnalysisCartStock() {
		return cartRepo.getAnalysisCartStock();
	}

	@GetMapping("/cart/top10/quantity")
	public List<AnalysisCartQuantity> getAnalysisCartQuantity() {
		return cartRepo.getAnalysisCartQuantity();
	}

	// 상품 통계/분석
	@GetMapping("/product/search/{fromDate}/{toDate}")
	public List<AnalysisProductDate> AnalysisProductDate(@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate) {
		return productRepo.AnalysisProductDate(fromDate, toDate);
	}

	@GetMapping("/product/top10/quantity/{fromDate}/{toDate}")
	public List<AnalysisProductSalesQuantity> getAnalysisProductSalesQuantity(@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate) {
		return productRepo.getAnalysisProductSalesQuantity(fromDate, toDate);
	}

	@GetMapping("/product/top10/sales/{fromDate}/{toDate}")
	public List<AnalysisProductTotalSales> getAnalysisProductTotalSales(@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate) {
		return productRepo.getAnalysisProductTotalSales(fromDate, toDate);
	}

	// 매출 통계/분석
	@GetMapping("/sales/search/{fromDate}/{toDate}")
	public List<AnalysisSalesDate> getAnalysisSalesDate(@PathVariable("fromDate") String fromDate,
			@PathVariable("toDate") String toDate) {
		return salesRepo.getAnalysisSalesDate(fromDate, toDate);
	}

}
