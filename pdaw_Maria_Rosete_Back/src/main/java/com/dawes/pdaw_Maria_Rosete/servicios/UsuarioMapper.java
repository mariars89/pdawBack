package com.dawes.pdaw_Maria_Rosete.servicios;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.dawes.pdaw_Maria_Rosete.dto.UsuarioDTO;

import com.dawes.pdaw_Maria_Rosete.modelo.UsuarioVO;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

	@Mapping(source = "rol.nombre", target = "rol")
	public UsuarioDTO toUsuarioDTO(UsuarioVO usuario);
	
	@Mapping(target = "authorities", ignore = true)
	@Mapping(target = "idusuario", ignore = true)
	public UsuarioVO toUsuario(UsuarioDTO usuarioDTO);
	
}
