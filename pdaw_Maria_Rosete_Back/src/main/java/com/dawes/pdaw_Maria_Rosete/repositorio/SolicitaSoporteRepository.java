package com.dawes.pdaw_Maria_Rosete.repositorio;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.dawes.pdaw_Maria_Rosete.modelo.SolicitaSoporteVO;
import com.dawes.pdaw_Maria_Rosete.modelo.UsuarioVO;

public interface SolicitaSoporteRepository extends JpaRepository<SolicitaSoporteVO, Integer>{
	@Query("SELECT s FROM SolicitaSoporteVO s WHERE s.usuario.id = :idusuario AND s.materialCurso.id = :idmaterial_curso")
	Optional<SolicitaSoporteVO> findByUsuarioAndMaterialCurso(int idusuario, int idmaterial_curso);
	
	 Iterable<SolicitaSoporteVO> findByUsuario(UsuarioVO usuario);
	
	

}
