package com.excilys.formation.java.projet.service;

import java.util.List;

import com.excilys.formation.java.projet.dao.*;
import com.excilys.formation.java.projet.modele.*;

public class CompanyService {

	private CompanyDAO cptdao = CompanyDAO.getInstance();
	private LogDAO logdao = LogDAO.getInstance();

	public CompanyService() {
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

}
