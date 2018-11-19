package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Funcionario;
import com.example.repository.FuncionarioRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public List<Funcionario> findAll() {
		return funcionarioRepository.findAll();
	}
	
	public Optional<Funcionario> findOne(Integer id) {
		return funcionarioRepository.findById(id);
	}
	
	@Transactional(readOnly = false)
	public Funcionario save(Funcionario entity) {
		return funcionarioRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Funcionario entity) {
		funcionarioRepository.delete(entity);
	}

}
	
