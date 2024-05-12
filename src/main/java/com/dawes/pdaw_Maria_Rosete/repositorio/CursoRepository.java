package com.dawes.pdaw_Maria_Rosete.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dawes.pdaw_Maria_Rosete.modelo.CursoVO;

import java.util.Optional;

/**
 * Repositorio para la entidad CursoVO. Proporciona metodos para interactuar con la tabla de cursos en la base de datos.
 * 
 * @author Maria Rosete
 */
@Repository
public interface CursoRepository extends JpaRepository<CursoVO, Integer> {

    /**
     * Busca un curso por su ID.
     *
     * @param idcurso El ID del curso a buscar.
     * @return Un Optional que puede contener al curso si se encuentra, o vacio si no.
     */
    Optional<CursoVO> findById(Integer idcurso);

    /**
     * Busca cursos por su titulo.
     *
     * @param titulo El titulo del curso a buscar.
     * @return Una coleccion iterable de cursos que tienen el titulo proporcionado.
     */
    Iterable<CursoVO> findByTitulo(String titulo);

    /**
     * Busca cursos por su categoria.
     *
     * @param categoria La categoria del curso a buscar.
     * @return Una coleccion iterable de cursos que tienen la categoria proporcionada.
     */
    Iterable<CursoVO> findByCategoria(String categoria);
}
