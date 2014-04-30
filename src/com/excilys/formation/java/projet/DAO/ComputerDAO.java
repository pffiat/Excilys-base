package com.excilys.formation.java.projet.DAO;

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

	public void insert(Computer comp, Connection conn, Statement stmt) throws SQLException {
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
		request(query, conn, stmt);
	}

	public void delete(Computer comp, Connection conn, Statement stmt) throws SQLException {

		String query = "DELETE FROM computer WHERE id=" + comp.getId();
		request(query, conn, stmt);
	}

	public void update(Computer comp, Connection conn, Statement stmt) throws SQLException {
		String query = "UPDATE computer " + "SET name='" + comp.getName()
				+ "', introduced='" + comp.getIntroduced()
				+ "', discontinued='" + comp.getDiscontinued()
				+ "', company_id='" + comp.getCompany().getId() + "' WHERE id="
				+ comp.getId();
		System.out.println(query);
		request(query, conn, stmt);
	}

	public List<Computer> getAll(Connection conn, Statement stmt,
			ResultSet results) throws SQLException {
		String query = "SELECT * FROM computer LEFT OUTER JOIN company ON computer.company_id = company.id";
		return select(query, conn, stmt, results);
	}

	public List<Computer> getCriteria(String criteria, Connection conn, Statement stmt,
			ResultSet results)
			throws SQLException {
		String query = "SELECT * FROM computer LEFT OUTER JOIN company ON computer.company_id = company.id "
				+ criteria;
		System.out.println(query);
		return select(query, conn, stmt, results);

	}

	private void request(String query, Connection conn, Statement stmt)
			throws SQLException {

		stmt = conn.createStatement();
		stmt.executeUpdate(query);

	}

	public List<Computer> select(String query, Connection conn, Statement stmt,
			ResultSet results) throws SQLException {

		List<Computer> liste = new ArrayList<Computer>();
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
		return liste;
	}

}
