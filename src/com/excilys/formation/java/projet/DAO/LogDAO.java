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
		if(cm == null) {
			ConnectionManager.getInstance();
		}		
		return INSTANCE;
	}

	private static ConnectionManager cm = null;
	public void insert(Log comp, Connection conn) { 
		String query = "INSERT INTO company (id , name) "
				+ "VALUES ('" + comp.getId() + "', '" + comp.getName() + "') ";
		
		request(query, conn);
	}
	
	public void delete(Log comp, Connection conn) {
		String query = "DELETE FROM company WHERE id=" + comp.getId();

		request(query, conn);
	}
	
	public void update(Log comp, Connection conn) {
		String query = "UPDATE company "
				+ "SET name=" + comp.getName() + "WHERE id=" + comp.getId();

		request(query, conn);
	}
	
	public List<Log> getAll(Connection conn) {
		String query = "SELECT * FROM company";		
		return select(query, conn); 
	}
	
	public List<Log> getCriteria(String criteria, Connection conn) {
		
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
	
	public List<Log> select(String query, Connection conn){

		List<Log> liste  = new ArrayList<Log>();	
		Statement stmt = null;
		ResultSet results = null;
		try {
			stmt = conn.createStatement();
			try {

				System.out.println("results:");
				results = stmt.executeQuery(query);		
				try {
					while(results.next()) {
						Log cp = new Log();
						cp.setId(new Integer(results.getInt(1)));
						cp.setName(results.getString(2));
						cp.setDate(ComputerMapper.stringToCalendar(results.getString(3)));
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
//
//	ResultSet results;
//	
//	public void insert(Log log) { 
//		String query = "INSERT INTO computer (name, introduced, discontinued, company_id) "
//				+ "VALUES ('"
//				+ log.getName() + "', '" 
//				+ log.getDate() + "');" ;
//		System.out.println("insert query: " + query);
//		request(query);		
//	}
//	
//
//	public void delete(Log log) {
//		
//		String query = "DELETE FROM computer WHERE id=" + log.getId();
//		request(query);
//	}
//
//	public void update(Log log) {
//		String query = "UPDATE computer "
//				+ "SET name='" + log.getName() + "', date='" + log.getDate() + "' WHERE id=" + log.getId();
//		System.out.println(query);
//		request(query);
//	}
//
//	public List<Log> getAll() {
//		String query = "SELECT * FROM log";
//		return select(query);
//	}
//
//
//	public List<Log> getCriteria(String criteria) {
//		String query = "SELECT * FROM log" + criteria;
//		System.out.println(query);
//		return select(query);
//
//	}
//
//
//	private void request(String query) {
//
//		Connection conn = ConnectionManager.getConnection();
//		Statement stmt = null;
//		try {
//			stmt = conn.createStatement();
//			try {
//				stmt.executeUpdate(query);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		} finally {
//			ConnectionManager.closeStatement(stmt);
//			ConnectionManager.closeConnection(conn);
//			ConnectionManager.closeResultSet(results);
//			try {
//				stmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//	}
//
//	public List<Log> select(String query) {
//
//		List<Log> liste  = new ArrayList<Log>();	
//		Statement stmt = null;
//		ResultSet results = null;
//		Connection conn = ConnectionManager.getConnection();
//		try {
//			stmt = conn.createStatement();
//			try {
//				results = stmt.executeQuery(query);		
//				try {
//					while(results.next()) {
//						Log cp = new Log();
//						
//						cp.setId(new Integer(results.getInt(1)));
//						cp.setName(results.getString(2));
//						cp.setDate(ComputerMapper.stringToCalendar(results.getString(3)));
//										
//						
//						liste.add(cp);
//					}
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		} finally {
//			ConnectionManager.closeStatement(stmt);
//			ConnectionManager.closeConnection(conn);
//			ConnectionManager.closeResultSet(results);
//		}
//		return liste;
//	}


}
