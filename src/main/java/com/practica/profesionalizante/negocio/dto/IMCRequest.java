package com.practica.profesionalizante.negocio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IMCRequest {
	
	private Long id;
	private Long usuario;
	private String nombre;
	private Double altura;
	private Double peso;
	private Double imc;
	private String estado;
}
