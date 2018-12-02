package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.OS;
import com.example.repository.OSRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OSService {

	@Autowired
	private OSRepository osRepository;

	public List<OS> findAll() {
		return osRepository.findAll();
	}
	
	public Optional<OS> findOne(Integer id) {
		return osRepository.findById(id);
	}
	
	@Transactional(readOnly = false)
	public OS save(OS entity) {
		return osRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(OS entity) {
		osRepository.delete(entity);
	}

}
	
