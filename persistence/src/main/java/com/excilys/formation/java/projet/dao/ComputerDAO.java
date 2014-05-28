package com.excilys.formation.java.projet.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.excilys.formation.java.projet.modele.Computer;

public interface ComputerDAO extends CrudRepository<Computer, Long>	 {

	
	public List<Computer> findByNameContaining(String name,Pageable pageable);

	public int countByNameContaining(String name);

}
