package com.dawes.pdaw_Maria_Rosete.repositorio;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dawes.pdaw_Maria_Rosete.modelo.RolVO;
import com.dawes.pdaw_Maria_Rosete.modelo.UsuarioVO;

public interface UsuarioRepository extends CrudRepository<UsuarioVO, Integer> {

	@Query("SELECT u FROM UsuarioVO u LEFT JOIN FETCH u.rol r WHERE u.nombre = :nombre")
	Optional<UsuarioVO> buscarPorNombreUsuario(String nombre);
	Optional<UsuarioVO> findById(Integer idusuario);
	UsuarioVO findByCorreo(String correo);
	List<UsuarioVO> findByRol(RolVO rol);
	
	
	
}
