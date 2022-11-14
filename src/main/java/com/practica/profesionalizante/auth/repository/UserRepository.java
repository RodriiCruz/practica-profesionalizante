package com.practica.profesionalizante.auth.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practica.profesionalizante.auth.entity.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, UUID> {
	Optional<Usuario> findByEmail(String email);

	Optional<Usuario> findByUsuario(String usuario);

	boolean existsByEmail(String email);

	boolean existsByUsuario(String usuario);
}
