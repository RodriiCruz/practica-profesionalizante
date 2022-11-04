package com.practica.profesionalizante.negocio.service;

import java.util.List;

import com.practica.profesionalizante.negocio.dto.IMCRequest;
import com.practica.profesionalizante.negocio.dto.IMCResponse;

public interface IMCService {

	IMCResponse guardarIMC(IMCRequest request);

	List<IMCResponse> listarIMC();
	
	IMCResponse editarIMC(Long id, IMCRequest request);

	void eliminarIMC(Long id);

}
