package com.excilys.formation.java.projet.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.java.projet.mapper.ComputerMapper;
import com.excilys.formation.java.projet.modele.Log;

public enum LogDAO {

	INSTANCE;

	private LogDAO() {
	}

	public static LogDAO getInstance() {
		return INSTANCE;
	}

	public void insert(Log log) {
		String query = "INSERT INTO log (name, date) " + "VALUES ('"
				+ log.getName() + "', '"
				+ ComputerMapper.calendarToString(log.getDate()) + "');";

		request(query);
	}

	public void delete(Log log) {
		String query = "DELETE FROM log WHERE id=" + log.getId();

		request(query);
	}

	public void update(Log log) {
		String query = "UPDATE log " + "SET name='" + log.getName()
				+ "', date='" + ComputerMapper.calendarToString(log.getDate())
				+ "' WHERE id=" + log.getId();

		request(query);
	}

	public List<Log> getAll() {
		String query = "SELECT * FROM logany";
		return select(query);
	}

	public List<Log> getCriteria(String criteria) {

		String query = "SELECT * FROM log WHERE " + criteria;
		return select(query);
	}

	private void request(String query) {

		Statement stmt;
		try {
			stmt = ConnectionManager.getConnectionThLocal().createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Log> select(String query) {

		List<Log> liste = new ArrayList<Log>();
		Statement stmt;
		try {
			stmt = ConnectionManager.getConnectionThLocal().createStatement();
			ResultSet results = stmt.executeQuery(query);
			while (results.next()) {
				Log cp = new Log();
				cp.setId(new Integer(results.getInt(1)));
				cp.setName(results.getString(2));
				cp.setDate(ComputerMapper.stringToCalendar(results.getString(3)));
				liste.add(cp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
	}

}
