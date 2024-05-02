package com.dawes.pdaw_Maria_Rosete.repositorio;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dawes.pdaw_Maria_Rosete.modelo.NombreRol;
import com.dawes.pdaw_Maria_Rosete.modelo.RolVO;

import lombok.NonNull;

public interface RolRepository extends CrudRepository<RolVO, Integer> {
    
	@Query("SELECT r FROM RolVO r WHERE r.nombre = :nombre")
    Optional<RolVO> findByNombre(NombreRol nombre);
}
