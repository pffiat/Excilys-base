package com.excilys.formation.java.projet.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.java.projet.common.Sort;
import com.excilys.formation.java.projet.modele.*;
import com.excilys.formation.java.projet.dao.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("computerDao")
public class ComputerDAOImpl implements ComputerDAO{

	@Autowired
	SessionFactory sessionFactory;

	
	public List<Computer> getAll() {	

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Computer LEFT OUTER JOIN Company");
		List<Computer> list = new ArrayList<Computer>();
		for(final Object o : query.list()) {
			list.add((Computer)o);
		}		
		return list;
	}

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

		if(sort == null){
			sort = new Sort();
			sort.setColumn("name");
			sort.setOrder("ASC");
		}
		StringBuilder request = new StringBuilder(80);
		request.append("from Computer as computer left outer join fetch computer.company as company ");
		List<Computer> list = new ArrayList<>();
		Query query;
		if(search != null && !("".equals(search))) {
			request.append(" WHERE computer.name LIKE ? OR company.name LIKE ? ").append(sort.toString());
			query = session.createQuery(request.toString()).setParameter(0, "%"+search+"%").setParameter(1, "%"+search+"%");
			query.setFirstResult(offset).setMaxResults(scope);
		}
		else{
			request.append(sort.toString());
			query = session.createQuery(request.toString());
		}	
		list = (List<Computer>)query.setFirstResult(offset).setMaxResults(scope).list();
		return list;
		
	}

	public int getNumberWithCriteria(String search) {

		int resultInt = 0;
		StringBuilder request = new StringBuilder(80);
		Session session = sessionFactory.getCurrentSession();
		Query query;
		request.append("SELECT COUNT(*) FROM Computer as computer LEFT OUTER JOIN computer.company as company");
		if(search != null && !("".equals(search))) {
			request.append(" WHERE computer.name LIKE ? OR company.name LIKE ?");
			query = session.createQuery(request.toString()).setParameter(0, "%"+search+"%").setParameter(1, "%"+search+"%");			
		}
		else{
			query = session.createQuery(request.toString());
			
		}
		resultInt = ((Long) query.list().get(0)).intValue();
		return resultInt;
	}


}


