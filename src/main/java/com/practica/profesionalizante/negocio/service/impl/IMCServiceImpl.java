package com.practica.profesionalizante.negocio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practica.profesionalizante.negocio.dto.IMCRequest;
import com.practica.profesionalizante.negocio.dto.IMCResponse;
import com.practica.profesionalizante.negocio.entity.IMC;
import com.practica.profesionalizante.negocio.mapper.IMCMapper;
import com.practica.profesionalizante.negocio.repository.IMCRepository;
import com.practica.profesionalizante.negocio.service.IMCService;

@Service
public class IMCServiceImpl implements IMCService {

	private IMCMapper mapper;
	private IMCRepository repository;

	public IMCServiceImpl(IMCMapper mapper, IMCRepository repository) {
		this.mapper = mapper;
		this.repository = repository;
	}

	@Transactional
	@Override
	public IMCResponse guardarIMC(IMCRequest request) {
		double imc = request.getPeso() / (Math.pow(request.getAltura(), 2));
		request.setImc(imc);
		
		
		if (imc < 18.5) {
			request.setEstado("Bajo peso");
		} else if (imc >= 18.5 && imc < 25) {
			request.setEstado("Peso normal");
		} else if (imc >= 25 && imc < 30) {
			request.setEstado("Sobrepeso");
		} else if (imc >= 30 && imc < 35) {
			request.setEstado("Obesidad grado I");
		} else if (imc >= 35 && imc < 40) {
			request.setEstado("Obesidad grado II");
		} else {
			request.setEstado("Obesidad grado III");
		}

		IMC entity = repository.save(mapper.requestToEntity(request));
		return mapper.entityToResponse(entity);
	}

	@Transactional(readOnly = true)
	@Override
	public List<IMCResponse> listarIMC() {
		List<IMCResponse> response = null;
		Optional<List<IMC>> result = Optional.of(repository.findAll());

		if (result.isPresent()) {
			response = mapper.getList(result.get());
		}

		return response;
	}

	@Transactional
	@Override
	public IMCResponse editarIMC(Long id, IMCRequest request) {
		IMCResponse response = null;
		IMC entity = new IMC();
		Optional<IMC> result = repository.findById(id);

		double imc = request.getPeso() / (Math.pow(request.getAltura(), 2));
		request.setImc(imc);
		
		if (imc < 18.5) {
			request.setEstado("Bajo peso");
		} else if (imc >= 18.5 && imc < 25) {
			request.setEstado("Peso normal");
		} else if (imc >= 25 && imc < 30) {
			request.setEstado("Sobrepeso");
		} else if (imc >= 30 && imc < 35) {
			request.setEstado("Obesidad grado I");
		} else if (imc >= 35 && imc < 40) {
			request.setEstado("Obesidad grado II");
		} else {
			request.setEstado("Obesidad grado III");
		}

		if (result.isPresent()) {
			entity.setId(result.get().getId());
			entity.setUsuario(result.get().getUsuario());
			entity.setNombre(request.getNombre());
			entity.setAltura(request.getAltura());
			entity.setPeso(request.getPeso());
			entity.setImc(request.getImc());
			entity.setEstado(request.getEstado());
			
			repository.save(entity);
			
			response = mapper.entityToResponse(entity);
		}

		return response;
	}

	@Transactional
	@Override
	public void eliminarIMC(Long id) {
		repository.deleteById(id);
	}

}
