package com.practica.profesionalizante.negocio.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.practica.profesionalizante.negocio.dto.IMCRequest;
import com.practica.profesionalizante.negocio.dto.IMCResponse;
import com.practica.profesionalizante.negocio.entity.IMC;

@Component
public class IMCMapper {
	
	public IMC requestToEntity(IMCRequest request) {
		return IMC.builder()
				.id(request.getId())
				.usuario(request.getUsuario())
				.nombre(request.getNombre())
				.peso(request.getPeso())
				.altura(request.getAltura())
				.imc(request.getImc())
				.estado(request.getEstado())
				.eliminado(false)
				.build();
	}
	
	public IMCResponse entityToResponse(IMC entity) {
		return IMCResponse.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.peso(entity.getPeso())
				.altura(entity.getAltura())
				.imc(entity.getImc())
				.estado(entity.getEstado())
				.build();
	}
	
	public List<IMCResponse> getList(List<IMC> entidades){
		List<IMCResponse> respuesta = new ArrayList<>();
		
		for (IMC imc : entidades) {
			respuesta.add(entityToResponse(imc));
		}
		
		return respuesta;
	}

}
