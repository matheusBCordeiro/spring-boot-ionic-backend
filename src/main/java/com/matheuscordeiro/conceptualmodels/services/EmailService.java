package com.matheuscordeiro.conceptualmodels.services;

import org.springframework.mail.SimpleMailMessage;

import com.matheuscordeiro.conceptualmodels.domain.Client;
import com.matheuscordeiro.conceptualmodels.domain.Order;

public interface EmailService {

	void sendOrderConfirmationEmail(Order obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Client cliente, String newPass);
}
