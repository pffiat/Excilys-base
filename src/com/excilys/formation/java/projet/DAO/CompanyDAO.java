package com.excilys.formation.java.projet.DAO;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.java.projet.modele.*;

public class CompanyDAO {

	private static ConnectionManager cm = null;
	
	/* singleton pattern*/
	private static CompanyDAO compDAO = null;
	private CompanyDAO() {	
		
	}
	synchronized public static CompanyDAO getInstance() {
		if(compDAO == null) {
			compDAO = new CompanyDAO();
			ConnectionManager.getInstance();
		}
		return compDAO;
	}	
	
	
	public void insert(Company comp) { 
		String query = "INSERT INTO company (id , name) "
				+ "VALUES ('" + comp.getId() + "', '" + comp.getName() + "') ";
		
		request(query);
	}
	
	public void delete(Company comp) {
		String query = "DELETE FROM company WHERE id=" + comp.getId();

		request(query);
	}
	
	public void update(Company comp) {
		String query = "UPDATE company "
				+ "SET name=" + comp.getName() + "WHERE id=" + comp.getId();

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
		ResultSet results = null;
		Connection conn = ConnectionManager.getConnection();
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
			ConnectionManager.closeConnection(conn);
			ConnectionManager.closeResultSet(results);			
		}
		
	}
	
	public List<Company> select(String query) {

		List<Company> liste  = new ArrayList<Company>();	
		Statement stmt = null;
		ResultSet results = null;
		Connection conn = ConnectionManager.getConnection();
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
			ConnectionManager.closeConnection(conn);
			ConnectionManager.closeResultSet(results);	
		}
		return liste;
	}

}
