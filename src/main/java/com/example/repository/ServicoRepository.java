package com.example.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.Servico;


@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {
	
}