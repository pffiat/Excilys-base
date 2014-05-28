package com.excilys.formation.java.projet.dao;
import org.springframework.data.repository.CrudRepository;

import com.excilys.formation.java.projet.modele.Company;

public interface CompanyDAO extends CrudRepository<Company, Long>	{ }
