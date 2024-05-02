package com.dawes.pdaw_Maria_Rosete.servicios;


import java.util.List;
import java.util.Optional;

import com.dawes.pdaw_Maria_Rosete.modelo.NombreRol;

import com.dawes.pdaw_Maria_Rosete.modelo.RolVO;

public interface ServicioRol {
	
	Optional<RolVO> findByNombreRol(NombreRol nombre);
	RolVO guardarRol(RolVO rol);
	RolVO actualizarRol(Integer id, RolVO rol);
	void eliminarRol(Integer id);
	RolVO obtenerRolPorId(Integer id);
	Iterable<RolVO> obtenerTodosLosRoles();
	Iterable<RolVO> saveAll(List<RolVO> roles);
	
	
	
	

	
}