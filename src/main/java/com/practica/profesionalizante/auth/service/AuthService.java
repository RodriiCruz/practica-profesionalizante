package com.practica.profesionalizante.auth.service;

import java.util.Optional;

import com.practica.profesionalizante.auth.dto.RegisterDto;
import com.practica.profesionalizante.auth.dto.RequestLoginDto;
import com.practica.profesionalizante.auth.dto.ResponseLoginDto;
import com.practica.profesionalizante.auth.entity.Usuario;

public interface AuthService {

	void register(RegisterDto registro);

    ResponseLoginDto login(RequestLoginDto login);

    Optional<Usuario> getUserByUsuario(String usuario);

    Optional<Usuario> getUserByEmail(String email);

    boolean existsUsuario(String usuario);

    boolean existsEmail(String email);

    void saveUser(Usuario usuario);
}
