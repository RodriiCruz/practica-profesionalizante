package com.practica.profesionalizante.negocio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practica.profesionalizante.negocio.dto.MailRequest;
import com.practica.profesionalizante.negocio.service.MailService;

@RestController
@RequestMapping("/api")
public class MailController {
	
	@Autowired
	private MailService service;
	
	@PostMapping("/contacto")
	public ResponseEntity<?> guardar(@RequestBody MailRequest request) {
		service.enviarMail(request);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
