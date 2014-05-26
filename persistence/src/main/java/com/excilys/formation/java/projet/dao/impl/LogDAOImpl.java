package com.excilys.formation.java.projet.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.formation.java.projet.modele.Log;
import com.excilys.formation.java.projet.dao.*;

@Repository("logDao")
public class LogDAOImpl implements LogDAO {

	@Autowired
	SessionFactory sessionFactory;

	public void insert(Log log) {
		Session session = sessionFactory.getCurrentSession();
		session.save(log);
	}
	
}