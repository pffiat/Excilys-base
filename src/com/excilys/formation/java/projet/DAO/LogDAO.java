package com.excilys.formation.java.projet.DAO;

import java.sql.Connection;
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

	public void insert(Log log, Connection conn, Statement stmt)  throws SQLException {
		String query = "INSERT INTO log (name, date) " + "VALUES ('"
				+ log.getName() + "', '" + ComputerMapper.calendarToString(log.getDate()) + "');";

		request(query, conn,stmt);
	}

	public void delete(Log log, Connection conn, Statement stmt) throws SQLException  {
		String query = "DELETE FROM log WHERE id=" + log.getId();

		request(query, conn,stmt);
	}

	public void update(Log log, Connection conn, Statement stmt) throws SQLException  {
		String query = "UPDATE log " + "SET name='" + log.getName()
				+ "', date='" + ComputerMapper.calendarToString(log.getDate()) + "' WHERE id=" + log.getId();

		request(query, conn,stmt);
	}

	public List<Log> getAll(Connection conn, Statement stmt,
			ResultSet results) throws SQLException  {
		String query = "SELECT * FROM logany";
		return select(query, conn, stmt, results);
	}

	public List<Log> getCriteria(String criteria, Connection conn, Statement stmt,
			ResultSet results) throws SQLException {

		String query = "SELECT * FROM log WHERE " + criteria;
		return select(query, conn, stmt, results);
	}

	private void request(String query, Connection conn, Statement stmt) throws SQLException {

		stmt = conn.createStatement();
		stmt.executeUpdate(query);

	}

	public List<Log> select(String query, Connection conn, Statement stmt,
			ResultSet results) throws SQLException {

		List<Log> liste = new ArrayList<Log>();
		stmt = conn.createStatement();

		System.out.println("results:");
		results = stmt.executeQuery(query);
		while (results.next()) {
			Log cp = new Log();
			cp.setId(new Integer(results.getInt(1)));
			cp.setName(results.getString(2));
			cp.setDate(ComputerMapper.stringToCalendar(results.getString(3)));
			liste.add(cp);
		}
		return liste;
	}

}
