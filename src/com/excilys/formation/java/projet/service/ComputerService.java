package com.excilys.formation.java.projet.service;

import java.util.List;

import com.excilys.formation.java.projet.dao.*;
import com.excilys.formation.java.projet.modele.*;

public class ComputerService {

	private ComputerDAO cptdao = ComputerDAO.getInstance();
	private LogDAO logdao = LogDAO.getInstance();
	private Computer cpt = null;

	public ComputerService() {
		cpt = new Computer();
		ConnectionManager.getInstance();
	}

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

	public List<Computer> getCriteria(String criteria) {
		List<Computer> comp = null;
		ConnectionManager.connection();
		comp = cptdao.getCriteria(criteria);
		logdao.insert(new Log("get computers with criteria:" + criteria));
		ConnectionManager.deconnection();
		return comp;
	}
}
