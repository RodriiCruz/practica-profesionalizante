package com.practica.profesionalizante.negocio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practica.profesionalizante.negocio.entity.IMC;

@Repository
public interface IMCRepository extends JpaRepository<IMC, Long>{

	Optional<List<IMC>> findByUsuario(Long usuario);
}
