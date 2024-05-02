package com.dawes.pdaw_Maria_Rosete.servicios;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.dawes.pdaw_Maria_Rosete.modelo.MaterialCursoVO;

public interface ServicioMaterialCurso {
	
	
	Optional <MaterialCursoVO> findByNombre(String nombre);
	

	<S extends MaterialCursoVO> S save(S entity);

	<S extends MaterialCursoVO> List<S> saveAll(Iterable<S> entities);

	<S extends MaterialCursoVO> Optional<S> findOne(Example<S> example);

	List<MaterialCursoVO> findAll(Sort sort);

	void flush();

	Page<MaterialCursoVO> findAll(Pageable pageable);

	<S extends MaterialCursoVO> S saveAndFlush(S entity);

	<S extends MaterialCursoVO> List<S> saveAllAndFlush(Iterable<S> entities);

	List<MaterialCursoVO> findAll();

	List<MaterialCursoVO> findAllById(Iterable<Integer> ids);

	void deleteInBatch(Iterable<MaterialCursoVO> entities);

	<S extends MaterialCursoVO> Page<S> findAll(Example<S> example, Pageable pageable);

	Optional<MaterialCursoVO> findById(Integer id);

	void deleteAllInBatch(Iterable<MaterialCursoVO> entities);

	boolean existsById(Integer id);

	<S extends MaterialCursoVO> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	<S extends MaterialCursoVO> boolean exists(Example<S> example);

	void deleteAllInBatch();

	MaterialCursoVO getOne(Integer id);

	<S extends MaterialCursoVO, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	long count();

	void deleteById(Integer id);

	MaterialCursoVO getById(Integer id);

	void delete(MaterialCursoVO entity);

	void deleteAllById(Iterable<? extends Integer> ids);

	MaterialCursoVO getReferenceById(Integer id);

	void deleteAll(Iterable<? extends MaterialCursoVO> entities);

	<S extends MaterialCursoVO> List<S> findAll(Example<S> example);

	<S extends MaterialCursoVO> List<S> findAll(Example<S> example, Sort sort);

	void deleteAll();

}