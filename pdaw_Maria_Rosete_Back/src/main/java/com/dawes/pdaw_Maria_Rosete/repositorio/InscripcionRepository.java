package com.dawes.pdaw_Maria_Rosete.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dawes.pdaw_Maria_Rosete.modelo.CursoVO;
import com.dawes.pdaw_Maria_Rosete.modelo.InscripcionVO;
import com.dawes.pdaw_Maria_Rosete.modelo.UsuarioVO;

@Repository
public interface InscripcionRepository extends JpaRepository<InscripcionVO, Integer>{
    Optional<InscripcionVO> findByUsuarioAndCurso(UsuarioVO usuario, CursoVO curso);

    Iterable<InscripcionVO> findByUsuario(UsuarioVO usuario);
}