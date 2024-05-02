package com.dawes.pdaw_Maria_Rosete.ws;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawes.pdaw_Maria_Rosete.modelo.SolicitaSoporteVO;
import com.dawes.pdaw_Maria_Rosete.modelo.UsuarioVO;
import com.dawes.pdaw_Maria_Rosete.servicios.ServicioSolicitaSoporte;
import com.dawes.pdaw_Maria_Rosete.servicios.ServicioUsuario;


@RestController
@RequestMapping("/solicitaSoporte")
public class SolicitaSoporteWS {
	
	@Autowired
	private ServicioSolicitaSoporte ssoporte;
	
    @Autowired
    private ServicioUsuario su;
	
	
	@PostMapping("/insertar")
	@PreAuthorize("hasAuthority('REGISTRADO')")
	public ResponseEntity<?> insertar(@RequestBody SolicitaSoporteVO solicitaSoporte){
		try {
			SolicitaSoporteVO soportes=ssoporte.save(solicitaSoporte);
			return new ResponseEntity<SolicitaSoporteVO>(soportes,HttpStatus.OK);
		}catch(Exception ex) {
	    	Map<String, Object> response = new HashMap<>();
			response.put("message", "Se ha producido un error al insertar un soporte " +ex.getCause());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    	
	    }
	}
	
/*************************************************************************************************************/	
	@DeleteMapping("/eliminar/{idsolicitaSoporte}")
	@PreAuthorize("hasAuthority('ADMINISTRADOR')")
	public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Integer idsolicitaSoporte) {
		Map<String, Object> response = new HashMap<>();

		try {
			ssoporte.deleteById(idsolicitaSoporte);
			response.put("message", "El material ha sido borrado");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		}
		catch(Exception e) {
			response.put("message", e.getMessage());
			return new ResponseEntity<Map<String, Object>> (response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}

/************************************************************************************************/
	
	
	@PutMapping("/modificar/{idSolicitud}")
	@PreAuthorize("hasAnyAuthority('REGISTRADO', 'ADMINISTRADOR')")
	
	public ResponseEntity<?> modificar(@RequestBody SolicitaSoporteVO solicitaSoporte,
	                                     @PathVariable int idSolicitud) {
	    Optional<SolicitaSoporteVO> solicitudToUpdate = ssoporte.findById(idSolicitud);
	    SolicitaSoporteVO updatedSolicitaSoporte;
	    Map<String, Object> response = new HashMap<>();

	    if (!solicitudToUpdate.isPresent()) {
	        response.put("message", "Error al modificar el formulario de solicitud de soporte: No se encontró el formulario de soporte");
	        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
	    }

	    try {
	        updatedSolicitaSoporte = solicitudToUpdate.get();
	        updatedSolicitaSoporte.setRespuesta(solicitaSoporte.getRespuesta());

	        updatedSolicitaSoporte = ssoporte.save(updatedSolicitaSoporte);
	    } catch (Exception ex) {
	        response.put("message", "Error al modificar el formulario de solicitud de soporte: " + ex.getMessage());
	        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    return new ResponseEntity<SolicitaSoporteVO>(updatedSolicitaSoporte, HttpStatus.OK);        
	}
/**************************************************************************************************************/
	@GetMapping("/buscarSoporte") 
	@PreAuthorize("hasAnyAuthority('REGISTRADO', 'ADMINISTRADOR')")
	
	public Iterable listaSoporte(){
		return ssoporte.findAll();
	}
	
	@GetMapping("/buscarSoporte/{idusuario}")
	@PreAuthorize("hasAnyAuthority('REGISTRADO', 'ADMINISTRADOR')")
	public ResponseEntity<?> buscarSoporte(@PathVariable Integer idusuario) {
	    try {
	        Optional<UsuarioVO> usuarioOptional = su.findById(idusuario);
	        if (usuarioOptional.isPresent()) {
	            UsuarioVO usuario = usuarioOptional.get();
	            Iterable<SolicitaSoporteVO> soportes = ssoporte.findByUsuario(usuario);
	            if (soportes.iterator().hasNext()) {
	                // Verificar si hay una respuesta del administrador
	                SolicitaSoporteVO soporte = soportes.iterator().next();
	                if (soporte.getRespuesta() != null && !soporte.getRespuesta().isEmpty()) {
	                    return ResponseEntity.ok(soporte);
	                } else {
	                    // Si no hay respuesta, devolver un mensaje indicando que no hay respuesta disponible
	                    return ResponseEntity.ok("No hay respuesta disponible del administrador para esta solicitud de soporte.");
	                }
	            } else {
	                return ResponseEntity.notFound().build();
	            }
	        } else {
	            Map<String, Object> response = new HashMap<>();
	            response.put("message", "El usuario no existe.");
	            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	        }
	    } catch (Exception ex) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("message", "Error al obtener el soporte: " + ex.getMessage());
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}





	
	
	
	
	





}
