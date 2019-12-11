package com.matheuscordeiro.conceptualmodels.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.matheuscordeiro.conceptualmodels.domain.Client;
import com.matheuscordeiro.conceptualmodels.domain.Order;

public interface EmailService {

	void sendOrderConfirmationEmail(Order obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Client cliente, String newPass);
	
	void sendOrderConfirmationHtmlEmail(Order obj);
	void sendHtmlEmail(MimeMessage msg);
}
