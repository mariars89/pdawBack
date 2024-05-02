package com.dawes.pdaw_Maria_Rosete.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dawes.pdaw_Maria_Rosete.modelo.MaterialCursoVO;

@Repository
public interface MaterialCursoRepository extends JpaRepository<MaterialCursoVO, Integer>{
	@Query("SELECT m FROM MaterialCursoVO m WHERE m.nombre = :nombre")
	Optional <MaterialCursoVO> findByNombre(String nombre);
	

}
