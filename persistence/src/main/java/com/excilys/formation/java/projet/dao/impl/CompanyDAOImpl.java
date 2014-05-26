package com.excilys.formation.java.projet.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.formation.java.projet.dao.CompanyDAO;
import com.excilys.formation.java.projet.modele.Company;

@Repository("companyDao")
public class CompanyDAOImpl implements CompanyDAO {


	@Autowired
	private SessionFactory sessionFactory;


	public List<Company> getAll() {	

		Session session = sessionFactory.getCurrentSession();
		Query query= session.createQuery("from Company");
		List<Company> list = new ArrayList<Company>();
		for(final Object o : query.list()) {
			list.add((Company)o);
		}		
		return list;
	}

}