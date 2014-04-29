package com.excilys.formation.java.projet.service;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.excilys.formation.java.projet.DAO.*;
import com.excilys.formation.java.projet.modele.*;

public class ComputerService {

	ComputerDAO cptdao;
	Computer cpt = null;
	private static ConnectionManager cm = null;

	public ComputerService() {
		cpt = new Computer();
		cptdao = cptdao.getInstance();
		if (cm == null) {
			ConnectionManager.getInstance();
		}
	}

	private void setComputer(Computer comp) {
		cpt = comp;
	}

	public void insertComputer(Computer comp) {
		Connection conn = null;
		try {
			conn = connection();
			this.cptdao.insert(comp, conn);
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

	public void updateComputer(Computer comp) {
		Connection conn = null;
		try {
			conn = connection();
			this.setComputer(comp);
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

	public void deleteComputer(Computer comp) {
		Connection conn = null;
		try {
			conn = connection();
			this.setComputer(comp);
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
		return "ComputerService toString";
	}

	public List<Computer> getAll() {
		Connection conn = null;
		List<Computer> comp = null;
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

	public List<Computer> getCriteria(String criteria) {
		Connection conn = null;
		List<Computer> comp = null;
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
