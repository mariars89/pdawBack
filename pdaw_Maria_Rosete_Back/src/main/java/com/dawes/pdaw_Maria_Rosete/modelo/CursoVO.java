package com.dawes.pdaw_Maria_Rosete.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Entity



@Table(name="cursos")
public class CursoVO {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true)
	private int idcurso;
	@NonNull
	private String descripcion;
	@NonNull
	private String categoria;
	@NonNull
	/*@Column(unique=true)*/
	private String titulo;
	
	
	/*@OneToMany(mappedBy = "curso")
	private List <InscripcionVO> inscripciones;
	@OneToMany(mappedBy = "curso")
	private List <MaterialCursoVO> materialcursos;*/

}
