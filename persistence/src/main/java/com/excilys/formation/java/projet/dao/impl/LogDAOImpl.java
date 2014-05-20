package com.excilys.formation.java.projet.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.formation.java.projet.modele.Log;
import com.excilys.formation.java.projet.dao.*;
import com.jolbox.bonecp.BoneCPDataSource;

@Repository("logDao")
public class LogDAOImpl implements LogDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void init(BoneCPDataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void insert(Log log) {
		String query = "INSERT INTO log (name, date) VALUES (?, ?);";
		request(query, log);
	}
	
	private void request(String query, Log log) {

			String date;
			if(log.getDate()!=null){
				date = log.getDate().toString();
			} else {
				date = null;
			}
			this.jdbcTemplate.update(query, log.getName(), date);

	}
}
