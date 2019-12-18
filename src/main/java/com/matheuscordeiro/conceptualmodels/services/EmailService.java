package com.matheuscordeiro.conceptualmodels.services;

import org.springframework.mail.SimpleMailMessage;

import com.matheuscordeiro.conceptualmodels.domain.Cliente;
import com.matheuscordeiro.conceptualmodels.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);

	void sendNewPasswordEmail(Cliente cliente, String newPass);;
}
