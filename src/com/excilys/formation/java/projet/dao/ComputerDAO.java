package com.excilys.formation.java.projet.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.java.projet.mapper.ComputerMapper;
import com.excilys.formation.java.projet.modele.*;
import java.sql.Connection;

public enum ComputerDAO {

	INSTANCE;

	private ComputerDAO() {
	}

	public static ComputerDAO getInstance() {
		return INSTANCE;
	}

	public void insert(Computer comp) {
		String query = "INSERT INTO computer (name, introduced, discontinued, company_id) "
				+ "VALUES ('"
				+ comp.getName()
				+ "', '"
				+ comp.getIntroduced()
				+ "', '"
				+ comp.getDiscontinued()
				+ "', '"
				+ comp.getCompany().getId() + "'); ";
		System.out.println("insert query: " + query);
		request(query);
	}

	public void delete(Computer comp) {

		String query = "DELETE FROM computer WHERE id=" + comp.getId();
		request(query);
	}

	public void update(Computer comp) {
		String query = "UPDATE computer " + "SET name='" + comp.getName()
				+ "', introduced='" + comp.getIntroduced()
				+ "', discontinued='" + comp.getDiscontinued()
				+ "', company_id='" + comp.getCompany().getId() + "' WHERE id="
				+ comp.getId();
		System.out.println(query);
		request(query);
	}

	public List<Computer> getAll() {
		String query = "SELECT * FROM computer LEFT OUTER JOIN company ON computer.company_id = company.id";
		return select(query);
	}

	public List<Computer> getCriteria(String criteria) {
		String query = "SELECT * FROM computer LEFT OUTER JOIN company ON computer.company_id = company.id "
				+ criteria;
		System.out.println(query);
		return select(query);

	}

	private void request(String query) {

		Statement stmt = null;
		try {
			stmt = ConnectionManager.getConnectionThLocal().createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeStatement(stmt);
		}

	}

	public List<Computer> select(String query) {

		List<Computer> liste = new ArrayList<Computer>();
		Connection conn = ConnectionManager.getConnectionThLocal();
		Statement stmt = null;
		ResultSet results = null;
		try {
			stmt = conn.createStatement();
			results = stmt.executeQuery(query);
			while (results.next()) {
				Computer cp = new Computer();

				cp.setId(new Integer(results.getInt(1)));
				cp.setName(results.getString(2));
				cp.setIntroduced(ComputerMapper.stringToCalendar(results
						.getString(3)));
				cp.setDiscontinued(ComputerMapper.stringToCalendar(results
						.getString(4)));

				Company comp = new Company();

				comp.setId(results.getInt(5));
				comp.setName(results.getString(7));
				cp.setCompany(comp);

				liste.add(cp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			ConnectionManager.closeResultSet(results);
			ConnectionManager.closeStatement(stmt);
		}
		return liste;
	}

}
