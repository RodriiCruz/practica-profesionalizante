package com.practica.profesionalizante.negocio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IMCResponse {
	
	private Long id;
	private String nombre;
	private Double altura;
	private Double peso;
	private Double imc;
	private String estado;

}
