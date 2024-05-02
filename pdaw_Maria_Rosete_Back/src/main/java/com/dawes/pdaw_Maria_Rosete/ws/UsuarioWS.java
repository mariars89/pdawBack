package com.dawes.pdaw_Maria_Rosete.ws;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawes.pdaw_Maria_Rosete.dto.UsuarioDTO;
import com.dawes.pdaw_Maria_Rosete.dto.UsuarioLoginDTO;
import com.dawes.pdaw_Maria_Rosete.modelo.UsuarioVO;
import com.dawes.pdaw_Maria_Rosete.servicios.ServicioUsuario;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioWS {
    
    @Autowired
    private ServicioUsuario su;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
   
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UsuarioLoginDTO usuarioLogin, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<String>("El usuario y la clave son obligatorios", HttpStatus.BAD_REQUEST);
        }

        UsuarioDTO usuarioDTO = su.login(usuarioLogin);
        if (usuarioDTO != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("token", usuarioDTO.getToken());
            response.put("idUsuario", usuarioDTO.getId());
            response.put("rol", usuarioDTO.getRol()); // Agregar el rol del usuario a la respuesta JSON
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }


    @PostMapping("/insertar")
    public ResponseEntity<?> insertar(@Valid @RequestBody UsuarioDTO usuario, BindingResult validaciones)
			throws Exception {

		if (validaciones.hasErrors()) {
			return new ResponseEntity<String>("Campos Imcompletos", HttpStatus.BAD_REQUEST);
		}
		
		try {
			return new ResponseEntity<UsuarioDTO>(su.crear(usuario), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    
    @DeleteMapping("/eliminar/{idusuario}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> eliminar(@PathVariable Integer idusuario){
        Map<String, Object> response= new HashMap<>();
                
        try {
            su.deleteById(idusuario);
            response.put("message", "El usuario ha sido borrado");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
        }
        catch(Exception e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<Map<String, Object>> (response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @PutMapping("/modificar/{idusuario}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'REGISTRADO')")
    public ResponseEntity<?> modificar(@RequestBody UsuarioVO usuario, @PathVariable Integer idusuario) {
        
    	Optional<UsuarioVO> usuarioToUpdate = su.findById(idusuario);
        UsuarioVO updatedUsuario;
        Map<String, Object> response = new HashMap<>();

        if (!usuarioToUpdate.isPresent()) {
            return errorResponse("Error al modificar el usuario: No se encontró el usuario a modificar", HttpStatus.NOT_FOUND);
        }

        try {
            updatedUsuario = usuarioToUpdate.get();
            updatedUsuario.setNombre(usuario.getNombre());
            updatedUsuario.setApellidos(usuario.getApellidos());
            updatedUsuario.setCorreo(usuario.getCorreo());
            updatedUsuario.setRol(usuario.getRol()); 

            // Encriptar la contraseña
            String contraseñaEncriptada = passwordEncoder.encode(usuario.getPassword());
            updatedUsuario.setPassword(contraseñaEncriptada);

            updatedUsuario = su.save(updatedUsuario);
            return ResponseEntity.ok(updatedUsuario);
            
        } catch (Exception ex) {
            return errorResponse("Error al modificar el usuario: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	    private ResponseEntity<?> errorResponse(String message, HttpStatus status) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("message", message);
	        return new ResponseEntity<>(response, status);
	    }

  /******************************************************************************************************************************/  
    @GetMapping("/buscarUsuarios")
    @PreAuthorize("hasAnyAuthority('REGISTRADO', 'ADMINISTRADOR')")
    
    public Iterable listaUsuarios(){
        return su.findAll();
    }
    
    @GetMapping("/buscarUsuarios/{idusuario}") 
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'REGISTRADO')")
    public Optional<UsuarioVO> findUsuario(@PathVariable Integer idusuario) {
        return su.findById(idusuario);
    }          
    
}

