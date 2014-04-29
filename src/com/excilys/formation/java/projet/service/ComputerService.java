package com.excilys.formation.java.projet.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.excilys.formation.java.projet.DAO.*;
import com.excilys.formation.java.projet.modele.*;

public class ComputerService {

	private static ComputerDAO cptdao = ComputerDAO.getInstance();
	private static LogDAO logdao = LogDAO.getInstance();
	private Computer cpt = null;
	private static ConnectionManager cm = null;

	public ComputerService() {
		cpt = new Computer();
		if (cm == null) {
			ConnectionManager.getInstance();
		}
	}

	private void setComputer(Computer comp) {
		cpt = comp;
	}

	public void insertComputer(Computer comp) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet results = null;
		try {
			conn = connection();
			this.cptdao.insert(comp, conn, stmt);
			this.logdao.insert(new Log("insert computer with id=" + comp.getId()) , conn, stmt);
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
	}

	public void updateComputer(Computer comp) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet results = null;
		try {
			conn = connection();
			this.setComputer(comp);
			this.cptdao.update(cpt, conn, stmt);
			this.logdao.insert(new Log("update computer with id=" + comp.getId()) , conn, stmt);
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
	}

	public void deleteComputer(Computer comp) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet results = null;
		try {
			conn = connection();
			this.setComputer(comp);
			this.cptdao.delete(comp, conn, stmt);
			this.logdao.insert(new Log("delete computer with id=" + comp.getId()) , conn, stmt);
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

	}

	public String toString() {
		return "ComputerService toString";
	}

	public List<Computer> getAll() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet results = null;
		List<Computer> comp = null;
		try {
			conn = connection();
			comp = cptdao.getAll(conn, stmt, results);
			this.logdao.insert(new Log("get all computers"), conn, stmt);
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

	public List<Computer> getCriteria(String criteria) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet results = null;
		List<Computer> comp = null;
		try {
			conn = connection();
			comp = cptdao.getCriteria(criteria, conn, stmt, results);
			this.logdao.insert(new Log("get computers with criteria:"+criteria), conn, stmt);
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
