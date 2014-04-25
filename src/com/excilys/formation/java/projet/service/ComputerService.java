package com.excilys.formation.java.projet.service;
import java.util.List;

import  com.excilys.formation.java.projet.DAO.*;
import  com.excilys.formation.java.projet.modele.*;

public class ComputerService {

	ComputerDAO cptdao;
	Computer cpt = null;
	
	public ComputerService() {
		cpt = new Computer();
		cptdao = cptdao.getInstance();
	}
	
	private void setComputer(Computer comp) {
		cpt = comp;
	}
	
	public void insertComputer(Computer comp) {
		System.out.println("ComputerService insertComputer:");
		System.out.println(comp.toString());
		this.cptdao.insert(comp);
	}
	
	public void updateComputer(Computer comp) {
		this.setComputer(comp);
		this.cptdao.update(cpt);
	}
	
	public void deleteComputer(Computer comp) {
		this.setComputer(comp);
		this.cptdao.delete(comp);
	}

//	public List<Computer> getAll(){
//		return ComputerDAO.g
//	}
	
	public String toString() {
		return "ComputerService toString";
	}

	public List<Computer> getAll() {
		
		return cptdao.getAll();
	}
	
	public List<Computer> getCriteria(String criteria) {
		return cptdao.getCriteria(criteria);
	}
}
