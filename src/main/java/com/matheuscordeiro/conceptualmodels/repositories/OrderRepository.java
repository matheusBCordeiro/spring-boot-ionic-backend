package com.matheuscordeiro.conceptualmodels.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matheuscordeiro.conceptualmodels.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

}
