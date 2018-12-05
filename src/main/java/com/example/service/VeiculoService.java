package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Veiculo;
import com.example.repository.VeiculoRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional(readOnly = true)
public class VeiculoService {

	@Autowired
	private VeiculoRepository veiculoRepository;

	public List<Veiculo> findAll() {
		return veiculoRepository.findAll();
	}
	
	public Optional<Veiculo> findOne(Integer id) {
		return veiculoRepository.findById(id);
	}
	
	@Transactional(readOnly = false)
	public Veiculo save(Veiculo entity) {
		if(!VeiculoService.isPlaca(entity.getPlaca()))
			return null;
		
		return veiculoRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(Veiculo entity) {
		veiculoRepository.delete(entity);
	}
	
	public static boolean isPlaca(String placa) {
		//Pattern pattern = Pattern.compile("[a-zA-Z]{3,3}-\\d{4,4}");
        //Matcher matcher = pattern.matcher("placa");
 
        if(placa.matches("[a-zA-Z]{3,3}-\\d{4,4}"))
        	return true;
        return false;
		
	}

}
	
