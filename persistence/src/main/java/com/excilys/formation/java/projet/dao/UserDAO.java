package com.excilys.formation.java.projet.dao;
import org.springframework.data.repository.CrudRepository;

import com.excilys.formation.java.projet.modele.User;

public interface UserDAO extends CrudRepository<User, Long>	{
	
	public User findByName(String name);
}

