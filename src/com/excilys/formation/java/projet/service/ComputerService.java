package com.excilys.formation.java.projet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.formation.java.projet.common.Sort;
import com.excilys.formation.java.projet.dao.*;
import com.excilys.formation.java.projet.dao.impl.ComputerDAOImpl;
import com.excilys.formation.java.projet.dao.impl.LogDAOImpl;
import com.excilys.formation.java.projet.modele.*;

@Service("computerService")
public class ComputerService {

	@Autowired
	private ComputerDAOImpl cptdao;
	@Autowired
	private LogDAOImpl logdao;
	private Computer cpt = new Computer();

	private void setComputer(Computer comp) {
		cpt = comp;
	}

	public void insertComputer(Computer comp) {
		ConnectionManager.connection();
		this.cptdao.insert(comp);
		this.logdao.insert(new Log("insert computer with id=" + comp.getId()));
		ConnectionManager.deconnection();
	}

	public void updateComputer(Computer comp) {
		ConnectionManager.connection();
		this.setComputer(comp);
		this.cptdao.update(cpt);
		this.logdao.insert(new Log("update computer with id=" + comp.getId()));
		ConnectionManager.deconnection();
	}

	public void deleteComputer(Computer comp) {
		ConnectionManager.connection();
		this.setComputer(comp);
		this.cptdao.delete(comp);
		this.logdao.insert(new Log("delete computer with id=" + comp.getId()));
		ConnectionManager.deconnection();

	}

	public String toString() {
		return "ComputerService toString";
	}

	public List<Computer> getAll() {
		List<Computer> comp = null;
		ConnectionManager.connection();
		comp = cptdao.getAll();
		this.logdao.insert(new Log("get all computers"));
		ConnectionManager.deconnection();
		return comp;
	}

	public List<Computer> getCriteria(String criteria, Sort sort, int i, int pageLimit) {
		List<Computer> comp = null;
		ConnectionManager.connection();
		comp = cptdao.getCriteria(criteria, sort, i, pageLimit);
		logdao.insert(new Log("get computers with criteria:" + criteria));
		ConnectionManager.deconnection();
		return comp;
	}

	public int getNumberWithCriteria(String criteria) {
		int count = 0;
		ConnectionManager.connection();
		count = cptdao.getNumberWithCriteria(criteria);
		logdao.insert(new Log("select count with criteria:" + criteria));
		ConnectionManager.deconnection();
		
		return count;
	}
}
