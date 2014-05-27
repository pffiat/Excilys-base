package com.excilys.formation.java.projet.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.excilys.formation.java.projet.common.Sort;
import com.excilys.formation.java.projet.modele.*;
import com.excilys.formation.java.projet.dao.*;

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
		Criteria criteria = session.createCriteria(Computer.class);
		if(sort == null){
			sort = new Sort();
			sort.setColumn("name");
			sort.setOrder("ASC");
		}
		
		if(sort.getOrder().equals("ASC")) {
			criteria.addOrder(Order.asc(sort.getColumn()));
		} else {
			criteria.addOrder(Order.desc(sort.getColumn()));
		}
		
		criteria.setMaxResults(scope).setFirstResult(offset);
		
		if(search != null && !("".equals(search))) {
			criteria.add( Restrictions.like("name", "%"+search+"%"));
			criteria.add( Restrictions.like("company.name", "%"+search+"%"));
		}
		List<Computer> list = new ArrayList<>();
		list = criteria.list();
		return list;
		
	}

	public int getNumberWithCriteria(String search) {

		int resultInt;
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Computer.class);
		if(search != null && !("".equals(search))) {
			criteria.add( Restrictions.like("name", "%"+search+"%"));
			criteria.add( Restrictions.like("company.name", "%"+search+"%"));			
		}
		
		resultInt = ((Long)criteria.setProjection( Projections.rowCount() ).uniqueResult()).intValue();
		return resultInt;
	}


}


