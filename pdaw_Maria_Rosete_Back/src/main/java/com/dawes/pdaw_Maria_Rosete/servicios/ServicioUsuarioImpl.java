package com.dawes.pdaw_Maria_Rosete.servicios;


import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dawes.pdaw_Maria_Rosete.dto.UsuarioDTO;
import com.dawes.pdaw_Maria_Rosete.dto.UsuarioLoginDTO;
import com.dawes.pdaw_Maria_Rosete.jwt.JwtProvider;
import com.dawes.pdaw_Maria_Rosete.modelo.NombreRol;
import com.dawes.pdaw_Maria_Rosete.modelo.RolVO;

import com.dawes.pdaw_Maria_Rosete.modelo.UsuarioVO;
import com.dawes.pdaw_Maria_Rosete.repositorio.RolRepository;
import com.dawes.pdaw_Maria_Rosete.repositorio.UsuarioRepository;

import lombok.NonNull;

@Service
public class ServicioUsuarioImpl implements ServicioUsuario {

	@Autowired
	private UsuarioRepository usuarioRepository;
		
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
    private RolRepository rolRepository;
	
	@Autowired
	private UsuarioMapper usuarioMapper;
	
	
	@Override
	public UsuarioDTO crear(UsuarioDTO usuarioDTO) throws Exception  {

		UsuarioVO usuario = usuarioMapper.toUsuario(usuarioDTO);
		usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
		RolVO rol = rolRepository.findByNombre(usuarioDTO.getRol()).orElseThrow(()-> new Exception("No existe el rol en la base de datos, inserte primero"));
		usuario.setRol(rol);
		usuario = usuarioRepository.save(usuario);
		return usuarioMapper.toUsuarioDTO(usuario);
		
	}

	@Override
	public UsuarioDTO login(UsuarioLoginDTO usuarioLoginDTO) {
	    // Autenticar al usuario
	    Authentication auth = authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(usuarioLoginDTO.getNombreUsuario(), usuarioLoginDTO.getClave()));
	    SecurityContextHolder.getContext().setAuthentication(auth);

	    // Obtener el usuario desde el repositorio
	    UsuarioVO usuario = usuarioRepository.buscarPorNombreUsuario(usuarioLoginDTO.getNombreUsuario()).orElse(null);
	    if (usuario == null) {
	        // Manejar el caso donde no se encuentra el usuario
	        return null;
	    }

	    // Obtener el nombre del rol del usuario
	    NombreRol nombreRol = usuario.getRol().getNombre(); // Suponiendo que la clase UsuarioVO tiene una relación con la clase RolVO

	    // Generar el token JWT incluyendo el nombre de usuario y el nuevo dato
	    String token = JwtProvider.generarTokenJWT(usuarioLoginDTO.getNombreUsuario(), usuario.getIdusuario());

	    // Mapear el usuario a un DTO
	    UsuarioDTO usuarioDTO = usuarioMapper.toUsuarioDTO(usuario);

	    // Asignar el nombre del rol y el token al DTO
	    usuarioDTO.setRol(nombreRol);
	    usuarioDTO.setToken(token);
	    return usuarioDTO;
	}





	@Override
    public void deleteById(Integer id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Optional<UsuarioVO> findById(Integer id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Iterable<UsuarioVO> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public UsuarioVO findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    @Override
    public List<UsuarioVO> findByRol(RolVO rol) {
        return usuarioRepository.findByRol(rol);
    }


    @Override
    public UsuarioVO save(UsuarioVO updatedUsuario) {
        return usuarioRepository.save(updatedUsuario);
    }

    @Override
    public Optional<UsuarioVO> buscarPorNombreUsuario(String nombre) {
        return usuarioRepository.buscarPorNombreUsuario(nombre);
    }

	

}