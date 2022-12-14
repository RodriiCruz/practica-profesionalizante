package com.practica.profesionalizante.negocio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.practica.profesionalizante.negocio.dto.IMCRequest;
import com.practica.profesionalizante.negocio.service.IMCService;

@RestController
@RequestMapping("/api")
public class IMCController {
	
	@Autowired
	private IMCService service;
	
	@PostMapping("/guardar")
	public ResponseEntity<?> guardar(@RequestHeader(name = "Authorization") String token, 
			@RequestBody IMCRequest request) throws JsonProcessingException{
		return new ResponseEntity<>(service.guardarIMC(token, request), HttpStatus.CREATED);
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(@RequestHeader(name = "Authorization") String token) 
			throws JsonProcessingException{
		return new ResponseEntity<>(service.listarIMC(token), HttpStatus.OK);
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<?> editar(@RequestHeader(name = "Authorization") String token, @PathVariable Long id, 
			@RequestBody IMCRequest request) throws JsonProcessingException{
		return new ResponseEntity<>(service.editarIMC(id, request), HttpStatus.OK);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminar(@RequestHeader(name = "Authorization") String token, @PathVariable Long id){
		service.eliminarIMC(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
