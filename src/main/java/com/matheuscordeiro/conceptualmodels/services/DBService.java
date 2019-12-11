package com.matheuscordeiro.conceptualmodels.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.matheuscordeiro.conceptualmodels.domain.Address;
import com.matheuscordeiro.conceptualmodels.domain.BilletPayment;
import com.matheuscordeiro.conceptualmodels.domain.CardPayment;
import com.matheuscordeiro.conceptualmodels.domain.Category;
import com.matheuscordeiro.conceptualmodels.domain.City;
import com.matheuscordeiro.conceptualmodels.domain.Client;
import com.matheuscordeiro.conceptualmodels.domain.Order;
import com.matheuscordeiro.conceptualmodels.domain.OrderItem;
import com.matheuscordeiro.conceptualmodels.domain.Payment;
import com.matheuscordeiro.conceptualmodels.domain.Product;
import com.matheuscordeiro.conceptualmodels.domain.State;
import com.matheuscordeiro.conceptualmodels.domain.enums.ClientType;
import com.matheuscordeiro.conceptualmodels.domain.enums.PaymentStatus;
import com.matheuscordeiro.conceptualmodels.domain.enums.Profile;
import com.matheuscordeiro.conceptualmodels.repositories.AddressRepository;
import com.matheuscordeiro.conceptualmodels.repositories.CategoryRepository;
import com.matheuscordeiro.conceptualmodels.repositories.CityRepository;
import com.matheuscordeiro.conceptualmodels.repositories.ClientRepository;
import com.matheuscordeiro.conceptualmodels.repositories.OrderItemRepository;
import com.matheuscordeiro.conceptualmodels.repositories.OrderRepository;
import com.matheuscordeiro.conceptualmodels.repositories.PaymentRepository;
import com.matheuscordeiro.conceptualmodels.repositories.ProductRepository;
import com.matheuscordeiro.conceptualmodels.repositories.StateRepository;

@Service
public class DBService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;

	public void instantiateTestDatabase() throws ParseException {
		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");

		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));

		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));

		State stt1 = new State(null, "Minas Gerais");
		State stt2 = new State(null, "São Paulo");

		City c1 = new City(null, "Uberlândia", stt1);
		City c2 = new City(null, "São Paulo", stt2);
		City c3 = new City(null, "Campinas", stt2);

		stt1.getCities().addAll(Arrays.asList(c1));
		stt2.getCities().addAll(Arrays.asList(c2, c3));

		stateRepository.saveAll(Arrays.asList(stt1, stt2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));

		Client cli1 = new Client(null, "Maria Silva", "nelio.cursos@gmail.com.br", "36378912377", ClientType.NATURALPERSON, pe.encode("123"));
		cli1.getPhones().addAll(Arrays.asList("27363323", "93838393"));
		
		
		Client cli2 = new Client(null, "Ana Costa", "nelio.iftm@gmail.com", "31628382740",ClientType.NATURALPERSON, pe.encode("123"));
		cli2.getPhones().addAll(Arrays.asList("93883321", "34252625"));
		cli2.addProfile(Profile.ADMIN);

		Address a1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Address a2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		Address a3 = new Address(null, "Avenida Floriano", "2106", null, "Centro", "281777012", cli2, c2);

		cli1.getAddresses().addAll(Arrays.asList(a1, a2));
		cli2.getAddresses().addAll(Arrays.asList(a3));

		clientRepository.saveAll(Arrays.asList(cli1,cli2));
		addressRepository.saveAll(Arrays.asList(a1, a2,a3));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Order ord1 = new Order(null, sdf.parse("30/09/2017 10:32"), cli1, a1);
		Order ord2 = new Order(null, sdf.parse("10/10/2017 19:35"), cli1, a2);

		Payment pay1 = new CardPayment(null, PaymentStatus.PAID, ord1, 6);
		ord1.setPayment(pay1);

		Payment pay2 = new BilletPayment(null, PaymentStatus.PENDING, ord2, sdf.parse("20/10/2017 00:00"), null);
		ord2.setPayment(pay2);

		cli1.getOrders().addAll(Arrays.asList(ord1, ord2));

		orderRepository.saveAll(Arrays.asList(ord1, ord2));
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));

		OrderItem oi1 = new OrderItem(ord1, p1, 0.00, 1, 2000.00);
		OrderItem oi2 = new OrderItem(ord1, p3, 0.00, 2, 80.00);
		OrderItem oi3 = new OrderItem(ord2, p2, 100.00, 1, 800.00);

		ord1.getItems().addAll(Arrays.asList(oi1, oi2));
		ord2.getItems().addAll(Arrays.asList(oi3));

		p1.getItems().addAll(Arrays.asList(oi1));
		p2.getItems().addAll(Arrays.asList(oi3));
		p3.getItems().addAll(Arrays.asList(oi2));

		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3));
	}
}