package com.practica.profesionalizante.negocio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "imcs")
@SQLDelete(sql = "UPDATE imcs SET eliminado = true WHERE id = ?")
@Where(clause = "eliminado = false")
public class IMC {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private Long usuario;
	private String nombre;
	private Double altura;
	private Double peso;
	private Double imc;
	private String estado;
	private boolean eliminado;

}
