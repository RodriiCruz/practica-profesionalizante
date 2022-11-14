package com.practica.profesionalizante.auth.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestLoginDto {

	@NotBlank(message = "Debe ingresar un nombre de usuario")
	private String usuario;
	
	@NotBlank(message = "Debe ingresar una contraseña")
	private String password;
}
