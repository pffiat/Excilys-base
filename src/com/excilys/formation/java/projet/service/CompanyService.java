package com.excilys.formation.java.projet.service;

import java.util.List;

import com.excilys.formation.java.projet.dao.*;
import com.excilys.formation.java.projet.modele.*;

public class CompanyService {

	private CompanyDAO cptdao = CompanyDAO.getInstance();
	private LogDAO logdao = LogDAO.getInstance();
	private Company cpt = null;

	public CompanyService() {
		// TODO Auto-generated constructor stub
	}

	private void setCompany(Company comp) {
		cpt = comp;
	}

	public void updateCompany(Company comp) {
		ConnectionManager.connection();
		this.setCompany(comp);
		this.cptdao.update(cpt);
		this.logdao.insert(new Log("update companies with id=" + comp.getId()));
		ConnectionManager.deconnection();
	}

	public void deleteCompany(Company comp) {
		ConnectionManager.connection();
		this.setCompany(comp);
		this.cptdao.delete(comp);
		this.logdao.insert(new Log("delete companies with id=" + comp.getId()));
		ConnectionManager.deconnection();
	}

	public String toString() {
		return "CompanyService toString";
	}

	public List<Company> getAll() {
		ConnectionManager.connection();
		List<Company> comp = null;
		comp = cptdao.getAll();
		this.logdao.insert(new Log("get all companies"));
		ConnectionManager.deconnection();
		return comp;
	}

	public List<Company> getCriteria(String criteria) {
		ConnectionManager.connection();
		List<Company> comp = null;
		comp = cptdao.getCriteria(criteria);
		this.logdao.insert(new Log("get companies with criteria:" + criteria));
		ConnectionManager.deconnection();
		return comp;
	}

}
