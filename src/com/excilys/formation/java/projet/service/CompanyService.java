package com.excilys.formation.java.projet.service;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import  com.excilys.formation.java.projet.DAO.*;
import  com.excilys.formation.java.projet.modele.*;

public class CompanyService {

	private CompanyDAO cptdao = CompanyDAO.getInstance();
	private LogDAO logdao = LogDAO.getInstance();
	private Company cpt = null;
	
	public CompanyService() {
		// TODO Auto-generated constructor stub
	}
	
	private void setCompany(Company comp) {
		cpt = comp;
	}

	public void updateCompany(Company comp) {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = connection();
			this.setCompany(comp);
			this.cptdao.update(cpt, conn, stmt);
			this.logdao.insert(new Log("update companies with id=" + comp.getId()) , conn, stmt);
			deconnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} finally {
			ConnectionManager.closeConnection(conn);
			ConnectionManager.closeStatement(stmt);
		}
	}

	public void deleteCompany(Company comp) {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = connection();
			this.setCompany(comp);
			this.cptdao.delete(comp, conn, stmt);
			this.logdao.insert(new Log("delete companies with id=" + comp.getId()) , conn, stmt);
			deconnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} finally {
			ConnectionManager.closeConnection(conn);
			ConnectionManager.closeStatement(stmt);
		}

	}

	public String toString() {
		return "CompanyService toString";
	}

	public List<Company> getAll() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet results = null;
		List<Company> comp = null;
		try {
			conn = connection();
			comp = cptdao.getAll(conn, stmt, results);
			this.logdao.insert(new Log("get all companies"), conn, stmt);
			deconnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} finally {
			ConnectionManager.closeConnection(conn);
			ConnectionManager.closeStatement(stmt);
			ConnectionManager.closeResultSet(results);
		}
		return comp;
	}

	public List<Company> getCriteria(String criteria) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet results = null;
		List<Company> comp = null;
		try {
			conn = connection();
			comp = cptdao.getCriteria(criteria, conn, stmt, results);
			this.logdao.insert(new Log("get companies with criteria:"+criteria), conn, stmt);
			deconnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} finally {
			ConnectionManager.closeConnection(conn);
			ConnectionManager.closeStatement(stmt);
			ConnectionManager.closeResultSet(results);
		}
		return comp;
	}
	
	private Connection connection() throws SQLException {
		Connection conn = ConnectionManager.getConnection();
		conn.setAutoCommit(false);
		return conn;

	}

	private void deconnection(Connection conn) throws SQLException {
		conn.commit();
		conn.setAutoCommit(true);
		
	}
	
	
}
