package com.dawes.pdaw_Maria_Rosete.servicios;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.dawes.pdaw_Maria_Rosete.modelo.CursoVO;
import com.dawes.pdaw_Maria_Rosete.modelo.InscripcionVO;
import com.dawes.pdaw_Maria_Rosete.modelo.UsuarioVO;
import com.dawes.pdaw_Maria_Rosete.repositorio.InscripcionRepository;


@Service
public class ServicioInscripcionImpl implements ServicioInscripcion {
	@Autowired
	private InscripcionRepository ar;

	@Override
	public <S extends InscripcionVO> S save(S entity) {
		return ar.save(entity);
	}

	@Override
	public <S extends InscripcionVO> List<S> saveAll(Iterable<S> entities) {
		return ar.saveAll(entities);
	}

	@Override
	public <S extends InscripcionVO> Optional<S> findOne(Example<S> example) {
		return ar.findOne(example);
	}

	@Override
	public List<InscripcionVO> findAll(Sort sort) {
		return ar.findAll(sort);
	}

	@Override
	public void flush() {
		ar.flush();
	}

	@Override
	public Page<InscripcionVO> findAll(Pageable pageable) {
		return ar.findAll(pageable);
	}

	@Override
	public <S extends InscripcionVO> S saveAndFlush(S entity) {
		return ar.saveAndFlush(entity);
	}

	@Override
	public <S extends InscripcionVO> List<S> saveAllAndFlush(Iterable<S> entities) {
		return ar.saveAllAndFlush(entities);
	}

	@Override
	public List<InscripcionVO> findAll() {
		return ar.findAll();
	}

	@Override
	public List<InscripcionVO> findAllById(Iterable<Integer> ids) {
		return ar.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<InscripcionVO> entities) {
		ar.deleteInBatch(entities);
	}

	@Override
	public <S extends InscripcionVO> Page<S> findAll(Example<S> example, Pageable pageable) {
		return ar.findAll(example, pageable);
	}

	@Override
	public Optional<InscripcionVO> findById(Integer id) {
		return ar.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<InscripcionVO> entities) {
		ar.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return ar.existsById(id);
	}

	@Override
	public <S extends InscripcionVO> long count(Example<S> example) {
		return ar.count(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		ar.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends InscripcionVO> boolean exists(Example<S> example) {
		return ar.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		ar.deleteAllInBatch();
	}

	@Override
	public InscripcionVO getOne(Integer id) {
		return ar.getOne(id);
	}

	@Override
	public <S extends InscripcionVO, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return ar.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return ar.count();
	}

	@Override
	public void deleteById(Integer id) {
		ar.deleteById(id);
	}

	@Override
	public InscripcionVO getById(Integer id) {
		return ar.getById(id);
	}

	@Override
	public void delete(InscripcionVO entity) {
		ar.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		ar.deleteAllById(ids);
	}

	@Override
	public InscripcionVO getReferenceById(Integer id) {
		return ar.getReferenceById(id);
	}

	@Override
	public void deleteAll(Iterable<? extends InscripcionVO> entities) {
		ar.deleteAll(entities);
	}

	@Override
	public <S extends InscripcionVO> List<S> findAll(Example<S> example) {
		return ar.findAll(example);
	}

	@Override
	public <S extends InscripcionVO> List<S> findAll(Example<S> example, Sort sort) {
		return ar.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		ar.deleteAll();
	}

	@Override
	public Optional<InscripcionVO> findByUsuarioAndCurso(UsuarioVO usuario, CursoVO curso) {
		// TODO Auto-generated method stub
		return ar.findByUsuarioAndCurso(usuario, curso);
	}

	@Override
	public Iterable<InscripcionVO> findByUsuario(UsuarioVO usuarioVO) {
		// TODO Auto-generated method stub
		 return ar.findByUsuario(usuarioVO);
	}

	
	



	

	

}
