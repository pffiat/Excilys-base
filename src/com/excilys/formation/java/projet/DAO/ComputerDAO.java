
package com.excilys.formation.java.projet.DAO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.excilys.formation.java.projet.mapper.ComputerMapper;
import com.excilys.formation.java.projet.modele.*;

public class ComputerDAO {

	private static ConnectionManager cm = null;
	ResultSet results;

	/* singleton pattern*/
	private static ComputerDAO compDAO = new ComputerDAO();

	private ComputerDAO() {
		if(cm == null) {
			cm = new ConnectionManager();
		}		
	}

	synchronized public static ComputerDAO getInstance() {
		if(compDAO == null) {
			compDAO = new ComputerDAO();
		}
		System.out.println("ComputerDAO getInstance");
		return compDAO;
	}	


	public void insert(Computer comp) { 
		String query = "INSERT INTO computer (name, introduced, discontinued, company_id) "
				+ "VALUES ('"
				+ comp.getName() + "', '" 
				+ comp.getIntroduced() + "', '" 
				+ comp.getDiscontinued() +"', '" 
				+ comp.getCompany().getId() + "'); ";
		System.out.println("insert query: " + query);
		request(query);		
	}

	public void delete(Computer comp) {
		
		String query = "DELETE FROM computer WHERE id=" + comp.getId();
		request(query);
	}

	public void update(Computer comp) {
		String query = "UPDATE computer "
				+ "SET name='" + comp.getName() + "', introduced='" + comp.getIntroduced() + "', discontinued='" + comp.getDiscontinued() + "', company_id='" + comp.getCompany().getId() + "' WHERE id=" + comp.getId();
		System.out.println(query);
		request(query);
	}

	public List<Computer> getAll() {
		String query = "SELECT * FROM computer LEFT OUTER JOIN company ON computer.company_id = company.id";
		return select(query);
	}


	public List<Computer> getCriteria(String criteria) {
		String query = "SELECT * FROM computer LEFT OUTER JOIN company ON computer.company_id = company.id " + criteria;
		System.out.println(query);
		return select(query);

	}


	private void request(String query) {

		cm.connection();
		Statement stmt = null;
		try {
			stmt = cm.conn.createStatement();
			try {
				stmt.executeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			//			results=null;
			//			cm.close(stmt, results);
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public List<Computer> select(String query) {

		List<Computer> liste  = new ArrayList<Computer>();	
		cm.connection();
		Statement stmt = null;
		ResultSet results = null;
		try {
			stmt = cm.conn.createStatement();
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
			cm.close(stmt, results);
		}
		return liste;
	}




}
