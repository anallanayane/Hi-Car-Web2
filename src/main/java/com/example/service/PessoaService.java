package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Pessoa;
import com.example.repository.PessoaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public List<Pessoa> findAll() {
		return pessoaRepository.findAll();
	}
	
	public Optional<Pessoa> findOne(Integer id) {
		return pessoaRepository.findById(id);
	}
	
	@Transactional(readOnly = false)
	public Pessoa save(Pessoa entity) {
		return pessoaRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Pessoa entity) {
		pessoaRepository.delete(entity);
	}

}
	
