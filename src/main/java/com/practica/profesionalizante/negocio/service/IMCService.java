package com.practica.profesionalizante.negocio.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.practica.profesionalizante.negocio.dto.IMCRequest;
import com.practica.profesionalizante.negocio.dto.IMCResponse;

public interface IMCService {

	IMCResponse guardarIMC(String token, IMCRequest request) throws JsonProcessingException;

	List<IMCResponse> listarIMC(String token) throws JsonProcessingException;
	
	IMCResponse editarIMC(Long id, IMCRequest request);

	void eliminarIMC(Long id);

}
