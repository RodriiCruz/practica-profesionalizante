package com.practica.profesionalizante.negocio.service;

import com.practica.profesionalizante.negocio.dto.MailRequest;

public interface MailService {
	void enviarMail(MailRequest request);
}
