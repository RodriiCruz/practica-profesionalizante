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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practica.profesionalizante.negocio.dto.IMCRequest;
import com.practica.profesionalizante.negocio.service.IMCService;

@RestController
@RequestMapping("/api")
public class IMCController {
	
	@Autowired
	private IMCService service;
	
	@PostMapping("/guardar")
	public ResponseEntity<?> guardar(@RequestBody IMCRequest request){
		return new ResponseEntity<>(service.guardarIMC(request), HttpStatus.CREATED);
	}
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(){
		return new ResponseEntity<>(service.listarIMC(), HttpStatus.OK);
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody IMCRequest request){
		return new ResponseEntity<>(service.editarIMC(id, request), HttpStatus.OK);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){
		service.eliminarIMC(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
