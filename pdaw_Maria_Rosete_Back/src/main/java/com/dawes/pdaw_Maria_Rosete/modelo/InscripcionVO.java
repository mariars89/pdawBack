package com.dawes.pdaw_Maria_Rosete.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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



@Table(name="inscripciones",uniqueConstraints = {@UniqueConstraint (columnNames = {"idusuario","idcurso"})})
public class InscripcionVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idinscripcion;
	@NonNull
	@ManyToOne
	@JoinColumn(name="idusuario")
	private UsuarioVO usuario;
	@NonNull
	@ManyToOne
	@JoinColumn(name="idcurso")
	private CursoVO curso;

}
