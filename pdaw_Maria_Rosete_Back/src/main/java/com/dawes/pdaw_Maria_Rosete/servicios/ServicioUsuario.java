package com.dawes.pdaw_Maria_Rosete.servicios;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.dawes.pdaw_Maria_Rosete.dto.UsuarioDTO;
import com.dawes.pdaw_Maria_Rosete.dto.UsuarioLoginDTO;
import com.dawes.pdaw_Maria_Rosete.modelo.RolVO;
import com.dawes.pdaw_Maria_Rosete.modelo.UsuarioVO;

public interface ServicioUsuario  {
	
		UsuarioDTO login(UsuarioLoginDTO usuarioLoginDTO);
		UsuarioDTO crear(UsuarioDTO usuarioDTO) throws Exception;
	    /************************************************************/
	 	 Optional<UsuarioVO> buscarPorNombreUsuario(String nombre);
	 	void deleteById(Integer id);
	    Optional<UsuarioVO> findById(Integer id);
	    Iterable<UsuarioVO> findAll();
	    UsuarioVO findByCorreo(String correo);
	    List<UsuarioVO> findByRol(RolVO rol);
	    UsuarioVO save(UsuarioVO updatedUsuario);
	    
	   

}
