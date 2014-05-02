package com.excilys.formation.java.projet.dao;

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

	public void insert(Company comp) {
		String query = "INSERT INTO company ( name) " + "VALUES ('"
				+ comp.getName() + "') ";

		request(query);
	}

	public void delete(Company comp) {
		String query = "DELETE FROM company WHERE id=" + comp.getId();

		request(query);
	}

	public void update(Company comp) {
		String query = "UPDATE company " + "SET name='" + comp.getName()
				+ "' WHERE id=" + comp.getId();

		request(query);
	}

	public List<Company> getAll() {
		String query = "SELECT * FROM company";
		return select(query);
	}

	public List<Company> getCriteria(String criteria) {

		String query = "SELECT * FROM company WHERE " + criteria;
		return select(query);
	}

	private void request(String query) {
		Statement stmt = null;
		try {
			stmt = ConnectionManager.getConnectionThLocal().createStatement();
			stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Company> select(String query) {

		List<Company> liste = new ArrayList<Company>();
		Statement stmt = null;
		ResultSet results = null;
		try {
			stmt = ConnectionManager.getConnectionThLocal().createStatement();

			results = stmt.executeQuery(query);
			while (results.next()) {
				Company cp = new Company();
				cp.setId(new Integer(results.getInt(1)));
				cp.setName(results.getString(2));
				liste.add(cp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
	}

}
