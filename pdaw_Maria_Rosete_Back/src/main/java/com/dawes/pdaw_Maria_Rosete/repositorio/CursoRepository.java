package com.dawes.pdaw_Maria_Rosete.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dawes.pdaw_Maria_Rosete.modelo.CursoVO;

@Repository
public interface CursoRepository extends JpaRepository<CursoVO,Integer>{
	
	Optional<CursoVO> findById(Integer idcurso);

	Iterable<CursoVO> findByTitulo(String titulo);

	Iterable<CursoVO> findByCategoria(String categoria);
	
	

}
