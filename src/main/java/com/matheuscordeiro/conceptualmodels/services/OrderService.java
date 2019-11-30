package com.matheuscordeiro.conceptualmodels.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheuscordeiro.conceptualmodels.domain.Order;
import com.matheuscordeiro.conceptualmodels.repositories.OrderRepository;
import com.matheuscordeiro.conceptualmodels.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;

	public Order find(Integer id) {

		Optional<Order> object = repository.findById(id);
		if(object == null) {
			throw new ObjectNotFoundException("Object not found! id:" + id
					+ ", Type:" + Order.class.getName());
		}
		return object.orElse(null);
	}

}
