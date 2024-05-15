package com.dawes.pdaw_Maria_Rosete.servicios;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.dawes.pdaw_Maria_Rosete.dto.UsuarioDTO;
import com.dawes.pdaw_Maria_Rosete.modelo.UsuarioVO;
import com.dawes.pdaw_Maria_Rosete.modelo.RolVO;

@Mapper(componentModel = "spring", uses = ServicioRol.class) // Indicamos que UsuarioMapper usa ServicioRol
public interface UsuarioMapper {

    @Mapping(source = "rol.nombre", target = "rol")
    @Mapping(target = "clave", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "token", ignore = true)
    UsuarioDTO toUsuarioDTO(UsuarioVO usuario);

    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "idusuario", ignore = true)
    UsuarioVO toUsuario(UsuarioDTO usuarioDTO);

    // Método abstracto para buscar RolVO por nombre
    RolVO findByNombreRol(String nombre);
}

