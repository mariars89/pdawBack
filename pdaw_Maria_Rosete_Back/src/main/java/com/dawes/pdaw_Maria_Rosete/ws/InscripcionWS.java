package com.dawes.pdaw_Maria_Rosete.ws;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawes.pdaw_Maria_Rosete.modelo.CursoVO;
import com.dawes.pdaw_Maria_Rosete.modelo.InscripcionVO;
import com.dawes.pdaw_Maria_Rosete.modelo.UsuarioVO;
import com.dawes.pdaw_Maria_Rosete.servicios.ServicioCurso;
import com.dawes.pdaw_Maria_Rosete.servicios.ServicioInscripcion;
import com.dawes.pdaw_Maria_Rosete.servicios.ServicioUsuario;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionWS {
	@Autowired
    private ServicioInscripcion si;
    
    @Autowired
    private ServicioUsuario su;
    
  
	
	
    @PostMapping("/insertar")
    @PreAuthorize("hasAuthority('REGISTRADO')")
    public ResponseEntity<?> insertar(@RequestBody InscripcionVO inscripcion){
        try {
            InscripcionVO insc = si.save(inscripcion);
            return new ResponseEntity<InscripcionVO>(insc, HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            // Captura la excepción de violación de integridad de datos
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Ya estás inscrito en este curso.");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            // Captura cualquier otra excepción
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Error al procesar la inscripción.");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	
	
/*************************************************************************************************************/	
	@DeleteMapping("/eliminar/{idinscripcion}")
	@PreAuthorize("hasAuthority('ADMINISTRADOR')")
	public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Integer idinscripcion) {
	    Map<String, Object> response = new HashMap<>();

		try {
			si.deleteById(idinscripcion);
			response.put("message", "La inscripción ha sido borrada");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		}
		catch(Exception e) {
			response.put("message", e.getMessage());
			return new ResponseEntity<Map<String, Object>> (response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}

/*******************************************************************************************************/
	@GetMapping("/buscarInscripciones")
	@PreAuthorize("hasAuthority('REGISTRADO')")
	
	public Iterable listaInscripciones(){
		return si.findAll();
	}
	
	@GetMapping("/buscarInscripcionesUsuario/{idusuario}")
	@PreAuthorize("hasAuthority('REGISTRADO')")
	public ResponseEntity<?> buscarInscripcionesUsuario(@PathVariable Integer idusuario) {
	    try {
	        Optional<UsuarioVO> usuario = su.findById(idusuario);
	        if (usuario.isPresent()) {
	            Iterable<InscripcionVO> inscripciones = si.findByUsuario(usuario.get());
	            return new ResponseEntity<Iterable<InscripcionVO>>(inscripciones, HttpStatus.OK);
	        } else {
	            Map<String, Object> response = new HashMap<>();
	            response.put("message", "El usuario no existe.");
	            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception ex) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("message", "Error al obtener las inscripciones.");
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}


	
	
	

}