package com.excilys.formation.java.projet.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.excilys.formation.java.projet.dao.*;
import com.excilys.formation.java.projet.modele.*;
import com.jolbox.bonecp.BoneCPDataSource;

@Repository("companyDao")
public class CompanyDAOImpl implements CompanyDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void init(BoneCPDataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}


	public List<Company> getAll() {	

		List<Company> liste = this.jdbcTemplate.query("SELECT * FROM company", new RowMapper<Company>() {
			public Company mapRow(ResultSet results, int rowNum) throws SQLException {

				Company cp = new Company();
				cp.setId(new Integer(results.getInt(1)));
				cp.setName(results.getString(2));
				return cp;
			}
		});

		return liste;
	}

}
