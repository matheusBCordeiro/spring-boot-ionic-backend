package com.matheuscordeiro.conceptualmodels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheuscordeiro.conceptualmodels.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
