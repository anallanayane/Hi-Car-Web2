package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Empresa;
import com.example.repository.EmpresaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;

	public List<Empresa> findAll() {
		return empresaRepository.findAll();
	}
	
	public Optional<Empresa> findOne(Integer id) {
		return empresaRepository.findById(id);
	}
	
	@Transactional(readOnly = false)
	public Empresa save(Empresa entity) {
		return empresaRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Empresa entity) {
		empresaRepository.delete(entity);
	}

}
	
