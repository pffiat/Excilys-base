package com.excilys.formation.java.projet.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.java.projet.modele.*;

public enum CompanyDAO {

	INSTANCE;

	private CompanyDAO() {

	}

	public static CompanyDAO getInstance() {
		return INSTANCE;
	}

	public void insert(Company comp, Connection conn, Statement stmt) throws SQLException  {
		String query = "INSERT INTO company ( name) " + "VALUES ('"
				+ comp.getName() + "') ";

		request(query, conn, stmt);
	}

	public void delete(Company comp, Connection conn, Statement stmt)  throws SQLException {
		String query = "DELETE FROM company WHERE id=" + comp.getId();

		request(query, conn, stmt);
	}

	public void update(Company comp, Connection conn, Statement stmt)  throws SQLException {
		String query = "UPDATE company " + "SET name='" + comp.getName()
				+ "' WHERE id=" + comp.getId();

		request(query, conn, stmt);
	}

	public List<Company> getAll(Connection conn, Statement stmt,
			ResultSet results) throws SQLException  {
		String query = "SELECT * FROM company";
		return select(query, conn, stmt, results);
	}

	public List<Company> getCriteria(String criteria, Connection conn, Statement stmt,
			ResultSet results) throws SQLException  {

		String query = "SELECT * FROM company WHERE " + criteria;
		return select(query, conn, stmt, results);
	}

	private void request(String query, Connection conn, Statement stmt) throws SQLException {

		stmt = conn.createStatement();
		stmt.executeQuery(query);

	}

	public List<Company> select(String query, Connection conn, Statement stmt,
			ResultSet results)
			throws SQLException {

		List<Company> liste = new ArrayList<Company>();
		stmt = conn.createStatement();

		System.out.println("results:");
		results = stmt.executeQuery(query);
		while (results.next()) {
			Company cp = new Company();
			cp.setId(new Integer(results.getInt(1)));
			cp.setName(results.getString(2));
			liste.add(cp);
		}
		return liste;
	}

}
