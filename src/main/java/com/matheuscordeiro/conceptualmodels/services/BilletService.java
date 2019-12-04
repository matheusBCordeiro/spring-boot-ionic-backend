package com.matheuscordeiro.conceptualmodels.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.matheuscordeiro.conceptualmodels.domain.BilletPayment;

@Service
public class BilletService {
	public void fillBilletPayment(BilletPayment pay, Date instantOrder) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(instantOrder);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		pay.setDueDate(calendar.getTime());
	}
}
