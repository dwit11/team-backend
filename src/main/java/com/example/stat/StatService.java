package com.example.stat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.stat.entity.AnalysisCart;
import com.example.stat.entity.AnalysisProduct;
import com.example.stat.entity.AnalysisSales;
import com.example.stat.event.Cart;
import com.example.stat.event.Product;
import com.example.stat.event.PurchaseOrder;
import com.example.stat.repository.CartRepository;
import com.example.stat.repository.ProductRepository;
import com.example.stat.repository.SalesRepository;
import com.google.gson.Gson;

@Service
public class StatService {

	private CartRepository cartRepo;
	private ProductRepository productRepo;
	private SalesRepository salesRepo;

	Random rand = new Random();
	Date time = new Date();
	SimpleDateFormat now = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	public StatService(CartRepository cartRepo, ProductRepository productRepo, SalesRepository salesRepo) {
		this.cartRepo = cartRepo;
		this.productRepo = productRepo;
		this.salesRepo = salesRepo;
	}

	@KafkaListener(topics = "commerce.cart", groupId = "etl")
	public void cart(String data) {
		System.out.println("<!-- Kafka Cart Log -->");
		System.out.println("commerce.cart : " + data);
		Cart cart = new Gson().fromJson(data, Cart.class);
		System.out.println("JSON : " + cart);

		if (cart.getProduct().getProductCode() != null && cart.getProduct().getPrice() != 0
				&& cart.getProduct().getName() != null) {
			int price = cart.getProduct().getPrice();
			String productName = cart.getProduct().getName();
			String productCode = cart.getProduct().getProductCode();

			AnalysisCart analysisCart = AnalysisCart.builder().productCode(productCode).productName(productName)
					.price(price).quantity(rand.nextInt(2) + 1).stock(rand.nextInt(150) + 100).numberMembers(1)
					.numberNonMembers(0).build();

			System.out.println(analysisCart);
			cartRepo.save(analysisCart);
		}
	}

	@KafkaListener(topics = "commerce.product", groupId = "etl")
	public void product(String data) {
		System.out.println("<!-- Kafka product Log -->");
		System.out.println("commerce.product : " + data);
		Product product = new Gson().fromJson(data, Product.class);
		System.out.println("JSON : " + product);

		if (product.getPrice() != null && product.getName() != null && product.getProductCode() != null
				&& product.getReDate() != null) {
			int paymentQuantity = rand.nextInt(1);
			int refundQuantity = rand.nextInt(paymentQuantity);
			int salesQuantity = paymentQuantity - refundQuantity;
			int price = product.getPrice();
			String productName = product.getName();
			String productCode = product.getProductCode();
			String reDate = product.getReDate();

			AnalysisProduct analysisProduct = AnalysisProduct.builder().reDate(reDate).productCode(productCode)
					.productName(productName).price(price).paymentQuantity(paymentQuantity)
					.refundQuantity(refundQuantity).salesQuantity(salesQuantity).totalSales(price * salesQuantity)
					.build();

			System.out.println(analysisProduct);
			productRepo.save(analysisProduct);
		}

	}

	@KafkaListener(topics = "commerce.purchaseorder", groupId = "etl")
	public void PurchaseOrder(String data) {
		System.out.println("<!-- Kafka Sales Log -->");
		System.out.println("commerce.purchaseorder : " + data);
		PurchaseOrder order = new Gson().fromJson(data, PurchaseOrder.class);
		System.out.println("JSON : " + order);

		if (order.getOrderDate() != null && order.getPmt() != 0) {

			long deliveryCharge = 2500;
			long cashDiscount = rand.nextInt(1) * 1000;
			long coupon = rand.nextInt(3) * 1000;
			long productPurchaseAmount = order.getPmt();
			long totalPayment = productPurchaseAmount + deliveryCharge - cashDiscount - coupon;

			AnalysisSales analysisSales = AnalysisSales.builder().orderDate(order.getOrderDate()).numberOrders(1)
					.numberItems(rand.nextInt(3) + 1).productPurchaseAmount(productPurchaseAmount)
					.deliveryCharge(deliveryCharge).cashDiscount(cashDiscount).coupon(coupon).totalPayment(totalPayment)
					.totalRefund(0).netSales(totalPayment).build();

			System.out.println(analysisSales);
			salesRepo.save(analysisSales);
		}
	}

}
