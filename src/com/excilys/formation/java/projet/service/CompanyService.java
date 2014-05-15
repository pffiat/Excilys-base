package com.excilys.formation.java.projet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.java.projet.dao.impl.CompanyDAOImpl;
import com.excilys.formation.java.projet.dao.impl.LogDAOImpl;
import com.excilys.formation.java.projet.modele.*;

@Service("companyService")
@Transactional
public class CompanyService {

	@Autowired
	private CompanyDAOImpl cptdao;
	@Autowired
	private LogDAOImpl logdao;

	public CompanyService() {
	}
	public String toString() {
		return "CompanyService toString";
	}

	@Transactional
	public List<Company> getAll() {
		List<Company> comp = null;
		comp = cptdao.getAll();
		this.logdao.insert(new Log("get all companies"));
		return comp;
	}

}
