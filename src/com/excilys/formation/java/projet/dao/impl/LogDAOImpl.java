package com.excilys.formation.java.projet.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.excilys.formation.java.projet.modele.Log;
import com.excilys.formation.java.projet.dao.*;

@Repository("logDao")
public class LogDAOImpl implements LogDAO {


	public void insert(Log log) {
		String query = "INSERT INTO log (name, date) VALUES (?, ?);";
		request(query, log);
	}
	private void request(String query, Log log) {

		PreparedStatement stmt;
		try {
			stmt = ConnectionManager.getConnectionThLocal().prepareStatement(query);
			stmt.setString(1, log.getName());
			stmt.setDate(2, new Date(log.getDate().getTime().getTime()));
			stmt.executeUpdate();
			System.out.println("insert log");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
