package com.excilys.formation.java.projet.service;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import  com.excilys.formation.java.projet.DAO.*;
import  com.excilys.formation.java.projet.modele.*;

public class CompanyService {

	CompanyDAO cptdao = null;
	Company cpt = null;
	
	public CompanyService() {
		// TODO Auto-generated constructor stub
	}
	
	private void setCompany(Company comp) {
		cpt = comp;
	}
//	
//	public void insertCompany(Company comp) {
//		this.setCompany(comp);
//		this.cpndao.insert(cpn);
//	}
//	
//	public void updateCompany(Company comp) {
//		this.setCompany(comp);
//		this.cpndao.update(cpn);
//	}
//	
//	public void deleteCompany(Company comp) {
//		this.setCompany(comp);
//		this.cpndao.delete(cpn);
//	}
//	
//	public List<Company> getAll() {
//		cpndao = cpndao.getInstance();
//		return cpndao.getAll();
//	}
//	public void insertCompany(Company comp) {
//		Connection conn = null;
//		try {
//			conn = connection();
//			this.cptdao.insert(comp, conn);
//			deconnection(conn);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			try {
//				conn.rollback();
//			} catch (SQLException ex) {
//				ex.printStackTrace();
//			}
//		}
//	}

	public void updateCompany(Company comp) {
		Connection conn = null;
		try {
			conn = connection();
			this.setCompany(comp);
			this.cptdao.update(cpt, conn);
			deconnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void deleteCompany(Company comp) {
		Connection conn = null;
		try {
			conn = connection();
			this.setCompany(comp);
			this.cptdao.delete(comp, conn);
			deconnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

	}

	public String toString() {
		return "CompanyService toString";
	}

	public List<Company> getAll() {
		Connection conn = null;
		List<Company> comp = null;
		try {
			conn = connection();
			comp = cptdao.getAll(conn);
			deconnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return comp;
	}

	public List<Company> getCriteria(String criteria) {
		Connection conn = null;
		List<Company> comp = null;
		try {
			conn = connection();
			comp = cptdao.getCriteria(criteria, conn);
			deconnection(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
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
