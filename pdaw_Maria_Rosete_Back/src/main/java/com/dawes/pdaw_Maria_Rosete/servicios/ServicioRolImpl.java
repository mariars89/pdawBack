package com.dawes.pdaw_Maria_Rosete.servicios;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.dawes.pdaw_Maria_Rosete.modelo.NombreRol;
import com.dawes.pdaw_Maria_Rosete.modelo.RolVO;
import com.dawes.pdaw_Maria_Rosete.repositorio.RolRepository;

@Service
public class ServicioRolImpl implements ServicioRol {
	@Autowired
    private RolRepository rolRepository;

	

	@Override
    public RolVO guardarRol(RolVO rol) {
        return rolRepository.save(rol);
    }

    @Override
    public RolVO actualizarRol(Integer id, RolVO rol) {
        RolVO rolExistente = obtenerRolPorId(id);
        if (rolExistente != null) {
            rol.setIdrol(id);
            return rolRepository.save(rol);
        }
        return null;
    }

    @Override
    public void eliminarRol(Integer id) {
        rolRepository.deleteById(id);
    }

    @Override
    public RolVO obtenerRolPorId(Integer id) {
        return rolRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<RolVO> obtenerTodosLosRoles() {
    	return rolRepository.findAll();
    }

    @Override
    public Iterable<RolVO> saveAll(List<RolVO> roles) {
        return rolRepository.saveAll(roles);
    }

	@Override
	public Optional<RolVO> findByNombreRol(NombreRol nombre) {
		// TODO Auto-generated method stub
		return rolRepository.findByNombre(nombre);
	}

	

}
