package com.matheuscordeiro.conceptualmodels.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.matheuscordeiro.conceptualmodels.domain.enums.PaymentStatus;

@Entity
@JsonTypeName("cardPayment")
public class CardPayment extends Payment{
	private static final long serialVersionUID = 1L;
	
	private Integer numberOfInstallments;
	
	public CardPayment() {
		
	}

	public CardPayment(Integer id, PaymentStatus status, Order order, Integer numberOfInstallments) {
		super(id, status, order);
		this.numberOfInstallments = numberOfInstallments;
	}

	public Integer getNumberOfInstallments() {
		return numberOfInstallments;
	}

	public void setNumberOfInstallments(Integer numberOfInstallments) {
		this.numberOfInstallments = numberOfInstallments;
	}
}
