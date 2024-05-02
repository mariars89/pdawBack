package com.dawes.pdaw_Maria_Rosete.ws;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dawes.pdaw_Maria_Rosete.modelo.NombreRol;
import com.dawes.pdaw_Maria_Rosete.modelo.RolVO;
import com.dawes.pdaw_Maria_Rosete.servicios.ServicioRol;


@RestController
@RequestMapping("/roles")
public class RolWS {

	@Autowired
	private ServicioRol sr;

	@PostMapping("/insertar")
	public ResponseEntity<?> insertar(@RequestBody List<RolVO> roles) {
	    try {
			Iterable<RolVO> rolesGuardados = sr.saveAll(roles);
		    return new ResponseEntity<>(rolesGuardados, HttpStatus.OK);
	    }catch(Exception ex) {
	    	Map<String, Object> response = new HashMap<>();
			response.put("message", "Se ha producido un error al insertar un rol " +ex.getCause());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    	
	    }
	}
	
/*********************************************************************************************************/	
	
	@DeleteMapping("/eliminar/{idrol}")
	public ResponseEntity<?> eliminar(@PathVariable Integer idrol){
		Map<String, Object> response= new HashMap<>();
				
		try {
			
			sr.eliminarRol(idrol);
			response.put("message", "El rol ha sido borrado");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		}
		catch(Exception e) {
			response.put("message", e.getMessage());
			return new ResponseEntity<Map<String, Object>> (response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
/*****************************************************************************************************************/	
	@PutMapping("/modificar/{nombre}")
	public ResponseEntity<?> modificar (@RequestBody RolVO rol, @PathVariable NombreRol nombre) {
				 
		Optional<RolVO> rolToUpdate = sr.findByNombreRol(nombre);
		RolVO updatedRol; 
		Map<String, Object> response = new HashMap<>();
		
		if(!rolToUpdate.isPresent()) {
			response.put("message", "Error al modificar el rol: No se encontró el rol a modificar");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			
			updatedRol = rolToUpdate.get(); 
			updatedRol.setNombre(rol.getNombre());		
	        updatedRol = sr.guardarRol(updatedRol);	
		}
		catch(Exception ex) {
			response.put("message", "Error al modificar el rol: "+ex.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

        return new ResponseEntity<RolVO>(updatedRol, HttpStatus.OK);		
	}		
/***********************************************************************************************************/
	@GetMapping("/buscarRoles") 
	
	public Iterable listaRoles(){
		return sr.obtenerTodosLosRoles();
	}
	
	@GetMapping("/buscarRoles/{nombre}") 
	public Optional<RolVO> findRol(@PathVariable NombreRol nombre) {
		return sr.findByNombreRol(nombre);
	}
	

}
