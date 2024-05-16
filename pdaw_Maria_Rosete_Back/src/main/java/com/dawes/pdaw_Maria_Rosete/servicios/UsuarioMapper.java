package com.dawes.pdaw_Maria_Rosete.servicios;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.dawes.pdaw_Maria_Rosete.dto.UsuarioDTO;
import com.dawes.pdaw_Maria_Rosete.modelo.UsuarioVO;

/**
 * Interfaz que define métodos para mapear entre objetos DTO y entidades de Usuario.
 * 
 * @autor Maria Rosete
 */
@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    /**
     * Convierte un objeto de tipo UsuarioVO a UsuarioDTO.
     * 
     * @param usuario El usuario a convertir.
     * @return El usuario convertido a DTO.
     */
    @Mapping(source = "rol.nombre", target = "rol")
    UsuarioDTO toUsuarioDTO(UsuarioVO usuario);

    /**
     * Convierte un objeto de tipo UsuarioDTO a UsuarioVO.
     * 
     * @param usuarioDTO El DTO de usuario a convertir.
     * @return El DTO de usuario convertido a entidad.
     */
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "idusuario", ignore = true)
    @Mapping(source = "rol", target = "rol")
    UsuarioVO toUsuario(UsuarioDTO usuarioDTO);
}

