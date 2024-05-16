package com.dawes.pdaw_Maria_Rosete.servicios;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.dawes.pdaw_Maria_Rosete.dto.UsuarioDTO;
import com.dawes.pdaw_Maria_Rosete.modelo.UsuarioVO;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(source = "rol.nombre", target = "rol")
    UsuarioDTO toUsuarioDTO(UsuarioVO usuario);

    @Mapping(source = "rol", target = "rol.nombre")
    @Mapping(target = "idusuario", ignore = true) // Ignora el id para operaciones de actualización
    UsuarioVO toUsuario(UsuarioDTO usuarioDTO);
}
