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
import com.dawes.pdaw_Maria_Rosete.repositorio.CursoRepository;



@Service
public class ServicioCursoImpl implements ServicioCurso {
	@Autowired
	private CursoRepository ar;

	@Override
	public <S extends CursoVO> S save(S entity) {
		return ar.save(entity);
	}

	@Override
	public <S extends CursoVO> List<S> saveAll(Iterable<S> entities) {
		return ar.saveAll(entities);
	}

	@Override
	public <S extends CursoVO> Optional<S> findOne(Example<S> example) {
		return ar.findOne(example);
	}

	@Override
	public List<CursoVO> findAll(Sort sort) {
		return ar.findAll(sort);
	}

	@Override
	public void flush() {
		ar.flush();
	}

	@Override
	public Page<CursoVO> findAll(Pageable pageable) {
		return ar.findAll(pageable);
	}

	@Override
	public <S extends CursoVO> S saveAndFlush(S entity) {
		return ar.saveAndFlush(entity);
	}

	@Override
	public <S extends CursoVO> List<S> saveAllAndFlush(Iterable<S> entities) {
		return ar.saveAllAndFlush(entities);
	}

	@Override
	public List<CursoVO> findAll() {
		return ar.findAll();
	}

	@Override
	public List<CursoVO> findAllById(Iterable<Integer> ids) {
		return ar.findAllById(ids);
	}

	@Override
	public void deleteInBatch(Iterable<CursoVO> entities) {
		ar.deleteInBatch(entities);
	}

	@Override
	public <S extends CursoVO> Page<S> findAll(Example<S> example, Pageable pageable) {
		return ar.findAll(example, pageable);
	}

	@Override
	public Optional<CursoVO> findById(Integer id) {
		return ar.findById(id);
	}

	@Override
	public void deleteAllInBatch(Iterable<CursoVO> entities) {
		ar.deleteAllInBatch(entities);
	}

	@Override
	public boolean existsById(Integer id) {
		return ar.existsById(id);
	}

	@Override
	public <S extends CursoVO> long count(Example<S> example) {
		return ar.count(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		ar.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends CursoVO> boolean exists(Example<S> example) {
		return ar.exists(example);
	}

	@Override
	public void deleteAllInBatch() {
		ar.deleteAllInBatch();
	}

	@Override
	public CursoVO getOne(Integer id) {
		return ar.getOne(id);
	}

	@Override
	public <S extends CursoVO, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
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
	public CursoVO getById(Integer id) {
		return ar.getById(id);
	}

	@Override
	public void delete(CursoVO entity) {
		ar.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		ar.deleteAllById(ids);
	}

	@Override
	public CursoVO getReferenceById(Integer id) {
		return ar.getReferenceById(id);
	}

	@Override
	public void deleteAll(Iterable<? extends CursoVO> entities) {
		ar.deleteAll(entities);
	}

	@Override
	public <S extends CursoVO> List<S> findAll(Example<S> example) {
		return ar.findAll(example);
	}

	@Override
	public <S extends CursoVO> List<S> findAll(Example<S> example, Sort sort) {
		return ar.findAll(example, sort);
	}

	@Override
	public void deleteAll() {
		ar.deleteAll();
	}

	 @Override
	    public Iterable<CursoVO> findByTitulo(String titulo) {
	        // Aquí podrías implementar alguna lógica adicional, si es necesario
	        return ar.findByTitulo(titulo);
	    }
	 @Override
	    public Iterable<CursoVO> findByCategoria(String categoria) {
	        // Aquí podrías implementar alguna lógica adicional, si es necesario
	        return ar.findByCategoria(categoria);
	    }
	

	


}

