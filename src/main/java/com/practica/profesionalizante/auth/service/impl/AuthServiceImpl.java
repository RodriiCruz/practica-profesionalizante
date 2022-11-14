package com.practica.profesionalizante.auth.service.impl;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practica.profesionalizante.auth.dto.RegisterDto;
import com.practica.profesionalizante.auth.dto.RequestLoginDto;
import com.practica.profesionalizante.auth.dto.ResponseLoginDto;
import com.practica.profesionalizante.auth.entity.Usuario;
import com.practica.profesionalizante.auth.exception.UserAlreadyExistsException;
import com.practica.profesionalizante.auth.exception.UserNotAuthenticationException;
import com.practica.profesionalizante.auth.jwt.JwtProvider;
import com.practica.profesionalizante.auth.repository.UserRepository;
import com.practica.profesionalizante.auth.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	private UserRepository repository;
	private AuthenticationManager authenticationManager;
	private JwtProvider jwtProvider;

	public AuthServiceImpl(UserRepository repository, AuthenticationManager authenticationManager,
			JwtProvider jwtProvider) {
		this.repository = repository;
		this.authenticationManager = authenticationManager;
		this.jwtProvider = jwtProvider;
	}

	@Override
	@Transactional
	public ResponseLoginDto register(RegisterDto registro) {
		if (existsUsuario(registro.getUsuario())) {
			throw new UserAlreadyExistsException("El usuario ya se encuentra registrado");
		}
		if (existsEmail(registro.getEmail())) {
			throw new UserAlreadyExistsException("El mail ya se encuentra registrado");
		}
		
		registro.setPassword(encriptarPass(registro.getPassword()));
		
		Usuario usuario = new Usuario();
		usuario.setActivo(true);
		usuario.setEmail(registro.getEmail());
		usuario.setUsuario(registro.getUsuario());
		usuario.setPassword(registro.getPassword());
		repository.save(usuario);
		
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseLoginDto login(RequestLoginDto login) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(login.getUsuario(), login.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);

		if (!authentication.isAuthenticated()) {
			throw new UserNotAuthenticationException("Usuario o contraseña incorrecto");
		}
		
		return new ResponseLoginDto(jwt);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> getUserByUsuario(String usuario) {
		return repository.findByUsuario(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> getUserByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existsUsuario(String usuario) {
		return repository.existsByUsuario(usuario);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existsEmail(String email) {
		return repository.existsByEmail(email);
	}

	@Override
	@Transactional
	public void saveUser(Usuario usuario) {
		repository.save(usuario);
	}

	private String encriptarPass(String pass) {
		return new BCryptPasswordEncoder().encode(pass);
	}
}
