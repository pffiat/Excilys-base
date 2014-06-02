package com.excilys.formation.java.projet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.java.projet.dao.*;
import com.excilys.formation.java.projet.modele.*;

@Service("userService")
@Transactional
public class UserService {


	@Autowired
	private UserDAO dao;
	@Autowired
	private LogDAO logdao;

	@Transactional
	public User findByName(String name) {
		User user = null;
		user =  dao.findByName(name);
		this.logdao.save(new Log("get user"));
		return user;
	}

}