package com.excilys.formation.java.projet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.java.projet.dao.*;
import com.excilys.formation.java.projet.modele.*;

@Service("companyService")
@Transactional
public class CompanyService {

	@Autowired
	private CompanyDAO cptdao;
	@Autowired
	private LogDAO logdao;

	@Transactional
	public List<Company> getAll() {
		List<Company> comp = null;
		comp = (List<Company>) cptdao.findAll();
		this.logdao.save(new Log("get all companies"));
		return comp;
	}

}