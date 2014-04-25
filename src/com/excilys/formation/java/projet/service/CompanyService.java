package com.excilys.formation.java.projet.service;
import java.util.List;

import  com.excilys.formation.java.projet.DAO.*;
import  com.excilys.formation.java.projet.modele.*;

public class CompanyService {

	CompanyDAO cpndao = null;
	Company cpn = null;
	
	public CompanyService() {
		// TODO Auto-generated constructor stub
	}
	
	private void setCompany(Company comp) {
		cpn = comp;
	}
	
	public void insertCompany(Company comp) {
		this.setCompany(comp);
		this.cpndao.insert(cpn);
	}
	
	public void updateCompany(Company comp) {
		this.setCompany(comp);
		this.cpndao.update(cpn);
	}
	
	public void deleteCompany(Company comp) {
		this.setCompany(comp);
		this.cpndao.delete(cpn);
	}
	
	public List<Company> getAll() {
		cpndao = cpndao.getInstance();
		return cpndao.getAll();
	}
}
