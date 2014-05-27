package com.excilys.formation.java.projet.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
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
		Criteria criteria = session.createCriteria(Company.class);
		
		List<Company> list = criteria.list();
		return list;
	}

}