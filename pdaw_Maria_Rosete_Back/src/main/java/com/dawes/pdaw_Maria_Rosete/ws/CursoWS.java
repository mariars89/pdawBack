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

import com.dawes.pdaw_Maria_Rosete.modelo.CursoVO;
import com.dawes.pdaw_Maria_Rosete.servicios.ServicioCurso;

@RestController
@RequestMapping("/cursos")
public class CursoWS {
	@Autowired
	private ServicioCurso sc;
	
	@PostMapping("/insertar")
	@PreAuthorize("hasAuthority('ADMINISTRADOR')")
	public ResponseEntity<?> insertar(@RequestBody CursoVO curso){
		try {
			CursoVO cur=sc.save(curso);
			return new ResponseEntity<CursoVO>(cur,HttpStatus.OK);
		}catch(Exception ex) {
	    	Map<String, Object> response = new HashMap<>();
			response.put("message", "Se ha producido un error al insertar un curso " +ex.getCause());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    	
	    }
	
	}
	
/************************************************************************************************************/	
	@DeleteMapping("/eliminar/{idcurso}")
	@PreAuthorize("hasAuthority('ADMINISTRADOR')")
	public ResponseEntity<?> eliminar(@PathVariable Integer idcurso){
				
		Map<String, Object> response = new HashMap<>();

		try {
			sc.deleteById(idcurso);
			response.put("message", "El curso ha sido borrado");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		}
		catch(Exception e) {
			response.put("message", e.getMessage());
			return new ResponseEntity<Map<String, Object>> (response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}

/***************************************************************************************************************/	
	@PutMapping("/modificar/{idtitulo}")
	@PreAuthorize("hasAuthority('ADMINISTRADOR')")
	public ResponseEntity<?> modificar (@RequestBody CursoVO curso, @PathVariable Integer idtitulo) {
				 
		Optional<CursoVO> cursoToUpdate = sc.findById(idtitulo);
		CursoVO updatedCurso; 
		Map<String, Object> response = new HashMap<>();
		
		if(!cursoToUpdate.isPresent()) {
			response.put("message", "Error al modificar el curso: No se encontró el curso a modificar");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		try {
			
			updatedCurso = cursoToUpdate.get(); // Obtener el curso a actualizar
			updatedCurso.setDescripcion(curso.getDescripcion());
			updatedCurso.setCategoria(curso.getCategoria());
			updatedCurso.setTitulo(curso.getTitulo());
			
	        updatedCurso = sc.save(updatedCurso);// Guardar el curso actualizado en la BBDD  	
		}
		catch(Exception ex) {
			response.put("message", "Error al modificar el curso: "+ex.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

        return new ResponseEntity<CursoVO>(updatedCurso, HttpStatus.OK);		
	}		
/***************************************************************************************************************/
	
	
	@GetMapping("/buscarCursos")
	@PreAuthorize("hasAnyAuthority('REGISTRADO', 'ADMINISTRADOR')")
	
	public Iterable listaCursos(){
		return sc.findAll();
	}
	
	@GetMapping("/buscarCursos/{idcurso}")
	 @PreAuthorize("hasAnyAuthority('REGISTRADO', 'ADMINISTRADOR')")
	public Optional<CursoVO> findCurso(@PathVariable Integer idcurso) {
		return sc.findById(idcurso);
	}
	
	@GetMapping("/buscarCursosPorTitulo/{titulo}") 
	public ResponseEntity<?> buscarCursosPorTitulo(@PathVariable String titulo) {
	    try {
	        Iterable<CursoVO> cursos;
	        
	        // Si se proporciona el parámetro "titulo"
	        if (titulo != null) {
	            cursos = sc.findByTitulo(titulo);
	        } 
	        // Si no se proporciona el parámetro "titulo", devolver todos los cursos
	        else {
	            cursos = sc.findAll();
	        }
	        
	        // Devolver la lista de cursos encontrados
	        return new ResponseEntity<Iterable<CursoVO>>(cursos, HttpStatus.OK);
	    } catch (Exception ex) {
	        // Manejar cualquier error que pueda ocurrir
	        Map<String, Object> response = new HashMap<>();
	        response.put("message", "Error al buscar cursos por título: " + ex.getMessage());
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@GetMapping("/buscarCursosPorCategoria/{categoria}") 
	public ResponseEntity<?> buscarCursosPorCategoria(@PathVariable String categoria) {
	    try {
	        Iterable<CursoVO> cursos;
	        
	        // Si se proporciona el parámetro "categoria"
	        if (categoria != null) {
	            cursos = sc.findByCategoria(categoria);
	        } 
	        // Si no se proporciona el parámetro "categoria", devolver todos los cursos
	        else {
	            cursos = sc.findAll();
	        }
	        
	        // Devolver la lista de cursos encontrados
	        return new ResponseEntity<Iterable<CursoVO>>(cursos, HttpStatus.OK);
	    } catch (Exception ex) {
	        // Manejar cualquier error que pueda ocurrir
	        Map<String, Object> response = new HashMap<>();
	        response.put("message", "Error al buscar cursos por categoría: " + ex.getMessage());
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	
	
	
	
	
}