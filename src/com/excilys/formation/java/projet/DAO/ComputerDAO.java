
package com.excilys.formation.java.projet.DAO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.excilys.formation.java.projet.mapper.ComputerMapper;
import com.excilys.formation.java.projet.modele.*;
import java.sql.Connection;

public enum ComputerDAO {
	
	INSTANCE;
	
	private ComputerDAO() {
	}
	
	private static ConnectionManager cm = null;
	ResultSet results;



	public static ComputerDAO getInstance() {
		if(cm == null) {
			ConnectionManager.getInstance();
		}
		System.out.println("ComputerDAO getInstance");
		return INSTANCE;
	}	


	public void insert(Computer comp, Connection conn) { 
		String query = "INSERT INTO computer (name, introduced, discontinued, company_id) "
				+ "VALUES ('"
				+ comp.getName() + "', '" 
				+ comp.getIntroduced() + "', '" 
				+ comp.getDiscontinued() +"', '" 
				+ comp.getCompany().getId() + "'); ";
		System.out.println("insert query: " + query);
		request(query, conn);		
	}

	public void delete(Computer comp, Connection conn) {
		
		String query = "DELETE FROM computer WHERE id=" + comp.getId();
		request(query, conn);
	}

	public void update(Computer comp, Connection conn) {
		String query = "UPDATE computer "
				+ "SET name='" + comp.getName() + "', introduced='" + comp.getIntroduced() + "', discontinued='" + comp.getDiscontinued() + "', company_id='" + comp.getCompany().getId() + "' WHERE id=" + comp.getId();
		System.out.println(query);
		request(query, conn);
	}

	public List<Computer> getAll(Connection conn) {
		String query = "SELECT * FROM computer LEFT OUTER JOIN company ON computer.company_id = company.id";
		return select(query, conn);
	}


	public List<Computer> getCriteria(String criteria, Connection conn) {
		String query = "SELECT * FROM computer LEFT OUTER JOIN company ON computer.company_id = company.id " + criteria;
		System.out.println(query);
		return select(query, conn);

	}


	private void request(String query, Connection conn) {

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			try {
				stmt.executeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			ConnectionManager.closeStatement(stmt);
			ConnectionManager.closeResultSet(results);
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public List<Computer> select(String query, Connection conn) {

		List<Computer> liste  = new ArrayList<Computer>();	
		Statement stmt = null;
		ResultSet results = null;
		try {
			stmt = conn.createStatement();
			try {
				results = stmt.executeQuery(query);		
				try {
					while(results.next()) {
						Computer cp = new Computer();
						
						cp.setId(new Integer(results.getInt(1)));
						cp.setName(results.getString(2));
						cp.setIntroduced(ComputerMapper.stringToCalendar(results.getString(3)));
						cp.setDiscontinued(ComputerMapper.stringToCalendar(results.getString(4)));
						
						Company comp = new Company();
						
						comp.setId(results.getInt(5));
						comp.setName(results.getString(7));
						cp.setCompany(comp);
						
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
