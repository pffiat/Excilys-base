package com.excilys.formation.java.projet.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.excilys.formation.java.projet.modele.Log;

public enum LogDAO {

	INSTANCE;

	private LogDAO() {
	}

	public static LogDAO getInstance() {
		return INSTANCE;
	}

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
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
