package com.excilys.formation.java.projet.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.excilys.formation.java.projet.modele.Log;
import com.excilys.formation.java.projet.dao.*;
import com.jolbox.bonecp.BoneCPDataSource;

@Repository("logDao")
public class LogDAOImpl implements LogDAO {

	@Autowired
	BoneCPDataSource ds;


	public void insert(Log log) {
		String query = "INSERT INTO log (name, date) VALUES (?, ?);";
		request(query, log);
	}
	
	private void request(String query, Log log) {

		PreparedStatement stmt = null;
		Connection conn = DataSourceUtils.getConnection(ds);
		try {
			stmt = conn.prepareStatement(query);
			stmt.setString(1, log.getName());
			if(log.getDate()!=null){
				stmt.setString(2, log.getDate().toString());
			} else {
				stmt.setString(2, null);
			}
			stmt.executeUpdate();
			System.out.println("insert log");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeStatement(stmt);
		}

	}
}
