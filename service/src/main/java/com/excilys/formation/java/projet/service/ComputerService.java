package com.excilys.formation.java.projet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.java.projet.common.Sort;
import com.excilys.formation.java.projet.dao.*;
import com.excilys.formation.java.projet.modele.Computer;
import com.excilys.formation.java.projet.modele.Log;

@Service("computerService")
@Transactional
public class ComputerService {

	@Autowired
	private ComputerDAO cptdao;
	@Autowired
	private LogDAO logdao;


	public void insertComputer(Computer comp) {
		this.cptdao.insert(comp);
		this.logdao.insert(new Log("insert computer with id=" + comp.getId()));
	}

	public void updateComputer(Computer comp) {
		this.cptdao.update(comp);
		this.logdao.insert(new Log("update computer with id=" + comp.getId()));
	}

	public void deleteComputer(Computer comp) {
		this.cptdao.delete(comp);
		this.logdao.insert(new Log("delete computer with id=" + comp.getId()));

	}

	public String toString() {
		return "ComputerService toString";
	}

	public List<Computer> getAll() {
		List<Computer> comp = null;
		comp = cptdao.getAll();
		this.logdao.insert(new Log("get all computers"));
		return comp;
	}

	public List<Computer> getCriteria(String criteria, Sort sort, int i, int pageLimit) {
		List<Computer> comp = null;
		comp = cptdao.getCriteria(criteria, sort, i, pageLimit);
		logdao.insert(new Log("get computers with criteria:" + criteria));
		return comp;
	}

	public int getNumberWithCriteria(String criteria) {
		int count = 0;
		count = cptdao.getNumberWithCriteria(criteria);
		logdao.insert(new Log("select count with criteria:" + criteria));		
		return count;
	}
}