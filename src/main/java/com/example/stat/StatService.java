package com.example.stat;

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

		int price = cart.getCartProducts().get(0).getPrice();
		String productName = cart.getCartProducts().get(0).getProduct().getName();

		AnalysisCart analysisCart = AnalysisCart.builder().productCode("P00000000").productName(productName)
				.price(price).quantity(rand.nextInt(3) + 1).stock(rand.nextInt(9) + 1)
				.numberMembers(rand.nextInt(9) + 1).numberNonMembers(0).build();

		System.out.println(analysisCart);
		cartRepo.save(analysisCart);
	}

	@KafkaListener(topics = "commerce.product", groupId = "etl")
	public void product(String data) {
		System.out.println("<!-- Kafka product Log -->");
		System.out.println("commerce.product : " + data);
		Product product = new Gson().fromJson(data, Product.class);
		System.out.println("JSON : " + product);

		// 판매 수량 = 결제수량 - 환불수량
		int paymentQuantity = rand.nextInt(9) + 1;
		int refundQuantity = rand.nextInt(2);
		int salesQuantity = paymentQuantity - refundQuantity;
		int price = product.getPrice();
		String productName = product.getName();
		String productCode = product.getProductCode();
		String reDate = product.getReDate();

		AnalysisProduct analysisProduct = AnalysisProduct.builder().reDate(reDate).dataUrl(null)
				.productCode(productCode).productName(productName).price(price).stock(rand.nextInt(9) + 1)
				.paymentQuantity(paymentQuantity).refundQuantity(refundQuantity).salesQuantity(salesQuantity)
				.totalSales(price * salesQuantity).build();
		System.out.println(analysisProduct);

		productRepo.save(analysisProduct);

	}

	@KafkaListener(topics = "commerce.purchaseorder", groupId = "etl")
	public void PurchaseOrder(String data) {
		System.out.println("<!-- Kafka Sales Log -->");
		System.out.println("commerce.purchaseorder : " + data);
		PurchaseOrder order = new Gson().fromJson(data, PurchaseOrder.class);
		System.out.println("JSON : " + order);

		int deliveryCharge = 2500;
		int cashDiscount = order.getOrderProducts().get(0).getCashDiscount();
		int coupon = 0;
		int productPurchaseAmount = order.getAmount();
		int totalPayment = productPurchaseAmount + deliveryCharge - cashDiscount - coupon;
		String orderDate = order.getOrderDate();

		AnalysisSales analysisSales = AnalysisSales.builder().orderDate(orderDate).numberOrders(rand.nextInt(2) + 1)
				.numberItems(rand.nextInt(3) + 1).productPurchaseAmount(productPurchaseAmount)
				.deliveryCharge(deliveryCharge).cashDiscount(cashDiscount).coupon(coupon).totalPayment(totalPayment)
				.totalRefund(0).netSales(totalPayment).build();

		System.out.println(analysisSales);
		salesRepo.save(analysisSales);

	}

}
