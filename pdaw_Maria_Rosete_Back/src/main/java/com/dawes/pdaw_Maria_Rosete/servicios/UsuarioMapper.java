package com.dawes.pdaw_Maria_Rosete.servicios;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.dawes.pdaw_Maria_Rosete.dto.UsuarioDTO;
import com.dawes.pdaw_Maria_Rosete.modelo.NombreRol;
import com.dawes.pdaw_Maria_Rosete.modelo.RolVO;
import com.dawes.pdaw_Maria_Rosete.modelo.UsuarioVO;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    public static final ServicioRol servicioRol = null;

    @Mapping(target = "clave", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "token", ignore = true)
    UsuarioDTO toUsuarioDTO(UsuarioVO usuario);

    @Mapping(target = "authorities", ignore = true)
    UsuarioVO toUsuario(UsuarioDTO usuarioDTO);

    default String rolToNombre(RolVO rol) {
        return rol.getNombre().toString();
    }

    default RolVO nombreToRol(NombreRol nombre) {
        return servicioRol.findByNombreRol(nombre).orElse(null);
    }
}
