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
		if(cm == null) {
			ConnectionManager.getInstance();
		}
		return INSTANCE;
	}	

	private static ConnectionManager cm = null;
	
	public void insert(Company comp, Connection conn) { 
		String query = "INSERT INTO company (id , name) "
				+ "VALUES ('" + comp.getId() + "', '" + comp.getName() + "') ";
		
		request(query, conn);
	}
	
	public void delete(Company comp, Connection conn) {
		String query = "DELETE FROM company WHERE id=" + comp.getId();

		request(query, conn);
	}
	
	public void update(Company comp, Connection conn) {
		String query = "UPDATE company "
				+ "SET name=" + comp.getName() + "WHERE id=" + comp.getId();

		request(query, conn);
	}
	
	public List<Company> getAll(Connection conn) {
		String query = "SELECT * FROM company";		
		return select(query, conn); 
	}
	
	public List<Company> getCriteria(String criteria, Connection conn) {
		
		String query = "SELECT * FROM company WHERE " + criteria;
		return select(query, conn);
	}


	
	private void request(String query, Connection conn) {
		
		Statement stmt = null;
		ResultSet results = null;
		try {
			stmt = conn.createStatement();
			try {
				results = stmt.executeQuery(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			ConnectionManager.closeStatement(stmt);
			ConnectionManager.closeResultSet(results);			
		}
		
	}
	
	public List<Company> select(String query, Connection conn){

		List<Company> liste  = new ArrayList<Company>();	
		Statement stmt = null;
		ResultSet results = null;
		try {
			stmt = conn.createStatement();
			try {

				System.out.println("results:");
				results = stmt.executeQuery(query);		
				try {
					while(results.next()) {
						Company cp = new Company();
						cp.setId(new Integer(results.getInt(1)));
						cp.setName(results.getString(2));
						liste.add(cp);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			ConnectionManager.closeStatement(stmt);
			ConnectionManager.closeResultSet(results);	
		}
		return liste;
	}

}
