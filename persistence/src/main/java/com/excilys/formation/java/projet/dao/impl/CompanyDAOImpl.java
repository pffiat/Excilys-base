package com.excilys.formation.java.projet.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.formation.java.projet.dao.CompanyDAO;
import com.excilys.formation.java.projet.modele.Company;
import com.excilys.formation.java.projet.modele.QCompany;
import com.mysema.query.jpa.hibernate.HibernateQuery;

@Repository("companyDao")
public class CompanyDAOImpl implements CompanyDAO {


	@Autowired
	private SessionFactory sessionFactory;


	public List<Company> getAll() {	

		Session session = sessionFactory.getCurrentSession();
		HibernateQuery query = new HibernateQuery (session);  
		QCompany company = QCompany.company;
		query.from(company);
		List<Company> list = query.list(company);
		return list;
	}

}