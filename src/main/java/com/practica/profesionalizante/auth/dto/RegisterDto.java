package com.practica.profesionalizante.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {
	
	@NotBlank(message = "Debe ingresar un nombre de usuario")
	private String usuario;
	
	@Email(message = "Debe ingresar un email válido")
	private String email;
	
	@NotBlank(message = "Debe ingresar una contraseña")
	private String password;

}
