package com.practica.profesionalizante.negocio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailRequest {
	
	private String nombre;
	private String email;
	private String asunto;
	private String mensaje;

}
