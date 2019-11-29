package com.matheuscordeiro.conceptualmodels.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.matheuscordeiro.conceptualmodels.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
