package com.excilys.formation.java.projet.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.excilys.formation.java.projet.common.Sort;
import com.excilys.formation.java.projet.modele.*;
import com.excilys.formation.java.projet.dao.*;
import com.mysema.query.jpa.hibernate.HibernateQuery;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("computerDao")
public class ComputerDAOImpl implements ComputerDAO{

	@Autowired
	SessionFactory sessionFactory;

	

	public void insert(Computer comp) {
		
		Session session = sessionFactory.getCurrentSession();
		session.persist(comp);
	}

	public void delete(Computer comp) {

		Session session = sessionFactory.getCurrentSession();
		session.delete(comp);
	}

	public void update(Computer comp) {
		
		Session session = sessionFactory.getCurrentSession();
		session.merge(comp);
		
	}


	public List<Computer> getCriteria(String search, Sort sort, int offset, int scope) {

		Session session = sessionFactory.getCurrentSession();
		HibernateQuery query = new HibernateQuery (session);  
		QComputer computer = QComputer.computer;
		query.from(computer);
		if(sort == null){
			sort = new Sort();
			sort.setColumn("name");
			sort.setOrder("ASC");
		}
		
		if(sort.getOrder().equals("ASC")) {
		    query.orderBy(computer.introduced.asc());
		} else {
		    query.orderBy(computer.introduced.desc());
		}
		
		if(search != null && !("".equals(search))) {
		    query.where(computer.name.like("%"+search+"%"), computer.company.name.like("%"+search+"%"));
		}
		query.offset(offset).limit(scope);
		List<Computer> list = query.list(computer);  
		return list;
		
	}

	public int getNumberWithCriteria(String search) {

		int resultInt;
		Session session = sessionFactory.getCurrentSession();
		HibernateQuery query = new HibernateQuery (session);  
		QComputer computer = QComputer.computer;
		query.from(computer);
		
		if(search != null && !("".equals(search))) {
		    query.where(computer.name.like("%"+search+"%"), computer.company.name.like("%"+search+"%"));
		}
		
		resultInt = ((Long)query.count()).intValue();
		return resultInt;
	}


}


