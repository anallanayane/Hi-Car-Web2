package com.example.repository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.OrdemServico;


@Repository
public interface OSRepository extends JpaRepository<OrdemServico, Integer> {
	
}