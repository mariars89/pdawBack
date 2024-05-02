package com.dawes.pdaw_Maria_Rosete.servicios;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;


import com.dawes.pdaw_Maria_Rosete.modelo.SolicitaSoporteVO;
import com.dawes.pdaw_Maria_Rosete.modelo.UsuarioVO;

public interface ServicioSolicitaSoporte {
	
	Optional<SolicitaSoporteVO> findByUsuarioAndMaterialCurso(int idusuario, int idmaterial_curso);
	
	<S extends SolicitaSoporteVO> S save(S entity);

	<S extends SolicitaSoporteVO> List<S> saveAll(Iterable<S> entities);

	<S extends SolicitaSoporteVO> Optional<S> findOne(Example<S> example);

	List<SolicitaSoporteVO> findAll(Sort sort);

	void flush();

	Page<SolicitaSoporteVO> findAll(Pageable pageable);

	<S extends SolicitaSoporteVO> S saveAndFlush(S entity);

	<S extends SolicitaSoporteVO> List<S> saveAllAndFlush(Iterable<S> entities);

	List<SolicitaSoporteVO> findAll();

	List<SolicitaSoporteVO> findAllById(Iterable<Integer> ids);

	void deleteInBatch(Iterable<SolicitaSoporteVO> entities);

	<S extends SolicitaSoporteVO> Page<S> findAll(Example<S> example, Pageable pageable);

	Optional<SolicitaSoporteVO> findById(Integer id);

	void deleteAllInBatch(Iterable<SolicitaSoporteVO> entities);

	boolean existsById(Integer id);

	<S extends SolicitaSoporteVO> long count(Example<S> example);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	<S extends SolicitaSoporteVO> boolean exists(Example<S> example);

	void deleteAllInBatch();

	SolicitaSoporteVO getOne(Integer id);

	<S extends SolicitaSoporteVO, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	long count();

	void deleteById(Integer id);

	SolicitaSoporteVO getById(Integer id);

	void delete(SolicitaSoporteVO entity);

	void deleteAllById(Iterable<? extends Integer> ids);

	SolicitaSoporteVO getReferenceById(Integer id);

	void deleteAll(Iterable<? extends SolicitaSoporteVO> entities);

	<S extends SolicitaSoporteVO> List<S> findAll(Example<S> example);

	<S extends SolicitaSoporteVO> List<S> findAll(Example<S> example, Sort sort);

	void deleteAll();

	 Iterable<SolicitaSoporteVO> findByUsuario(UsuarioVO usuario);
	
	

	

}