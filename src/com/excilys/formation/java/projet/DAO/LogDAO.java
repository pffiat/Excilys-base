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
		if (cm == null) {
			ConnectionManager.getInstance();
		}
		return INSTANCE;
	}

	private static ConnectionManager cm = null;

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
	//
	// ResultSet results;
	//
	// public void insert(Log log) {
	// String query =
	// "INSERT INTO log (name, introduced, discontinued, logany_id) "
	// + "VALUES ('"
	// + log.getName() + "', '"
	// + log.getDate() + "');" ;
	// System.out.println("insert query: " + query);
	// request(query);
	// }
	//
	//
	// public void delete(Log log) {
	//
	// String query = "DELETE FROM log WHERE id=" + log.getId();
	// request(query);
	// }
	//
	// public void update(Log log) {
	// String query = "UPDATE log "
	// + "SET name='" + log.getName() + "', date='" + log.getDate() +
	// "' WHERE id=" + log.getId();
	// System.out.println(query);
	// request(query);
	// }
	//
	// public List<Log> getAll() {
	// String query = "SELECT * FROM log";
	// return select(query);
	// }
	//
	//
	// public List<Log> getCriteria(String criteria) {
	// String query = "SELECT * FROM log" + criteria;
	// System.out.println(query);
	// return select(query);
	//
	// }
	//
	//
	// private void request(String query) {
	//
	// Connection conn = ConnectionManager.getConnection();
	// Statement stmt = null;
	// try {
	// stmt = conn.createStatement();
	// try {
	// stmt.executeUpdate(query);
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// } catch (SQLException e1) {
	// e1.printStackTrace();
	// } finally {
	// ConnectionManager.closeStatement(stmt);
	// ConnectionManager.closeConnection(conn);
	// ConnectionManager.closeResultSet(results);
	// try {
	// stmt.close();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// }
	//
	// public List<Log> select(String query) {
	//
	// List<Log> liste = new ArrayList<Log>();
	// Statement stmt = null;
	// ResultSet results = null;
	// Connection conn = ConnectionManager.getConnection();
	// try {
	// stmt = conn.createStatement();
	// try {
	// results = stmt.executeQuery(query);
	// try {
	// while(results.next()) {
	// Log cp = new Log();
	//
	// cp.setId(new Integer(results.getInt(1)));
	// cp.setName(results.getString(2));
	// cp.setDate(ComputerMapper.stringToCalendar(results.getString(3)));
	//
	//
	// liste.add(cp);
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// } catch (SQLException e1) {
	// e1.printStackTrace();
	// } finally {
	// ConnectionManager.closeStatement(stmt);
	// ConnectionManager.closeConnection(conn);
	// ConnectionManager.closeResultSet(results);
	// }
	// return liste;
	// }

}
