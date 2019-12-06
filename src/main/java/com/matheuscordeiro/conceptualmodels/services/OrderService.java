package com.matheuscordeiro.conceptualmodels.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheuscordeiro.conceptualmodels.domain.BilletPayment;
import com.matheuscordeiro.conceptualmodels.domain.Order;
import com.matheuscordeiro.conceptualmodels.domain.OrderItem;
import com.matheuscordeiro.conceptualmodels.domain.enums.PaymentStatus;
import com.matheuscordeiro.conceptualmodels.repositories.OrderItemRepository;
import com.matheuscordeiro.conceptualmodels.repositories.OrderRepository;
import com.matheuscordeiro.conceptualmodels.repositories.PaymentRepository;
import com.matheuscordeiro.conceptualmodels.services.exceptions.ObjectNotFoundException;
import com.matheuscordeiro.conceptualmodels.services.BilletService;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private BilletService billetService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private EmailService emailService;
	
	public Order find(Integer id) {

		Optional<Order> object = repository.findById(id);
		if(object == null) {
			throw new ObjectNotFoundException("Object not found! id:" + id
					+ ", Type:" + Order.class.getName());
		}
		return object.orElse(null);
	}
	
	public Order insert(Order object) {
		object.setId(null);
		object.setInstant(new Date());
		object.setClient(clientService.find(object.getClient().getId()));
		object.getPayment().setStatus(PaymentStatus.PENDING);
		object.getPayment().setOrder(object);
		if (object.getPayment() instanceof BilletPayment) {
			BilletPayment pay = (BilletPayment) object.getPayment();
			billetService.fillBilletPayment(pay, object.getInstant());
		}
		
		object = repository.save(object);
		paymentRepository.save(object.getPayment());
		for (OrderItem ip : object.getItems()) {
			ip.setDiscount(0.0);
			ip.setProduct(productService.find(ip.getProduct().getId()));
			ip.setPrice(ip.getProduct().getPrice());
			ip.setOrder(object);
		}
		orderItemRepository.saveAll(object.getItems());
		emailService.sendOrderConfirmationEmail(object);
		return object;
	}

}
