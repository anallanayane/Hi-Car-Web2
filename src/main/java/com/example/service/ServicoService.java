package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Servico;
import com.example.repository.ServicoRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ServicoService {

	@Autowired
	private ServicoRepository servicoRepository;

	public List<Servico> findAll() {
		return servicoRepository.findAll();
	}
	
	public Optional<Servico> findOne(Integer id) {
		return servicoRepository.findById(id);
	}
	
	@Transactional(readOnly = false)
	public Servico save(Servico entity) {
		return servicoRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Servico entity) {
		servicoRepository.delete(entity);
	}

}
	
