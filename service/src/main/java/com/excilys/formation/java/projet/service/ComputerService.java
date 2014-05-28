package com.excilys.formation.java.projet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		this.cptdao.save(comp);
		this.logdao.save(new Log("insert computer with id=" + comp.getId()));
	}

	public void updateComputer(Computer comp) {
		this.cptdao.save(comp);
		this.logdao.save(new Log("update computer with id=" + comp.getId()));
	}

	public void deleteComputer(Computer comp) {
		this.cptdao.delete(comp);
		this.logdao.save(new Log("delete computer with id=" + comp.getId()));

	}

	public String toString() {
		return "ComputerService toString";
	}

	public List<Computer> getCriteria(String criteria, Sort sort, int i, int pageLimit) {
		List<Computer> comp = null;
		Pageable pageable = new PageRequest(i , pageLimit);
		if( criteria == null || "".equals(criteria)){
			comp = (List<Computer>) cptdao.findByNameContaining("", pageable);
		} else {
			comp = cptdao.findByNameContaining(criteria, pageable);
		}
		logdao.save(new Log("get computers with criteria:" + criteria));
		return comp;
	}

	public int getNumberWithCriteria(String criteria) {
		int count;
		if( criteria == null || "".equals(criteria)){
			count =  ((Long)cptdao.count()).intValue();
		} else {
			count = cptdao.countByNameContaining(criteria);
		}
		logdao.save(new Log("select count with criteria:" + criteria));		
		return count;
	}
}