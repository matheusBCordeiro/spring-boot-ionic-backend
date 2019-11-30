package com.matheuscordeiro.conceptualmodels.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.matheuscordeiro.conceptualmodels.domain.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{

}
