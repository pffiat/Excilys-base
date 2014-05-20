package com.excilys.formation.java.projet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.java.projet.common.Sort;
import com.excilys.formation.java.projet.dao.impl.ComputerDAOImpl;
import com.excilys.formation.java.projet.dao.impl.LogDAOImpl;
import com.excilys.formation.java.projet.modele.*; 

@Service("computerService")
@Transactional
public class ComputerService {

	@Autowired
	private ComputerDAOImpl cptdao;
	@Autowired
	private LogDAOImpl logdao;
	private Computer cpt = new Computer();

	private void setComputer(Computer comp) {
		cpt = comp;
	}

	@Transactional
	public void insertComputer(Computer comp) {
		/*
		 * first getConnection() to initialize the connection to the datasource 
		 * the other calls of getConnection() will get the same connection
		 */
		this.cptdao.insert(comp);
		this.logdao.insert(new Log("insert computer with id=" + comp.getId()));
	}

	@Transactional
	public void updateComputer(Computer comp) {
		this.setComputer(comp);
		this.cptdao.update(cpt);
		this.logdao.insert(new Log("update computer with id=" + comp.getId()));
	}

	@Transactional
	public void deleteComputer(Computer comp) {
		this.setComputer(comp);
		this.cptdao.delete(comp);
		this.logdao.insert(new Log("delete computer with id=" + comp.getId()));

	}

	@Transactional
	public String toString() {
		return "ComputerService toString";
	}

	@Transactional
	public List<Computer> getAll() {
		List<Computer> comp = null;
		comp = cptdao.getAll();
		this.logdao.insert(new Log("get all computers"));
		return comp;
	}

	@Transactional
	public List<Computer> getCriteria(String criteria, Sort sort, int i, int pageLimit) {
		List<Computer> comp = null;
		comp = cptdao.getCriteria(criteria, sort, i, pageLimit);
		logdao.insert(new Log("get computers with criteria:" + criteria));
		return comp;
	}

	@Transactional
	public int getNumberWithCriteria(String criteria) {
		int count = 0;
		count = cptdao.getNumberWithCriteria(criteria);
		logdao.insert(new Log("select count with criteria:" + criteria));		
		return count;
	}
}
