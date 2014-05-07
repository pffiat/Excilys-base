package com.excilys.formation.java.projet.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.java.projet.common.Sort;
import com.excilys.formation.java.projet.mapper.ComputerMapper;
import com.excilys.formation.java.projet.modele.*;
import com.excilys.formation.java.projet.dao.*;

import java.sql.Connection;

import org.springframework.stereotype.Repository;

@Repository("computerDao")
public class ComputerDAOImpl implements ComputerDAO{


	public void insert(Computer comp) {
		String query = "INSERT INTO computer (name, introduced, discontinued, company_id) "
				+ "VALUES (?,?,?,?'); ";
		request(query, comp, "insert");
	}

	public void delete(Computer comp) {

		String query = "DELETE FROM computer WHERE id=?";
		request(query, comp, "delete");
	}

	public void update(Computer comp) {
		String query = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
		request(query, comp, "update");
	}

	public List<Computer> getAll() {
		String query = "SELECT * FROM computer LEFT OUTER JOIN company ON computer.company_id = company.id";
		return select(query);
	}

	public List<Computer> getCriteria(String search, Sort sort, int offset, int scope) {

		List<Computer> liste = new ArrayList<Computer>();
		Connection conn = ConnectionManager.getConnectionThLocal();
		PreparedStatement stmt = null;
		ResultSet results = null;
		StringBuilder query = new StringBuilder(80);
		query.append("SELECT * FROM computer LEFT OUTER JOIN company ON computer.company_id = company.id");
		try {
			if(search != null && !("".equals(search))) {
				query.append(" WHERE computer.name LIKE ? OR company.name LIKE ?");
				if(sort.needOrder()) { query.append(" ORDER BY ? ?"); }
				query.append(" LIMIT ?,?") ;
				stmt = conn.prepareStatement(query.toString());
				stmt.setString(1, "%"+search+"%");
				stmt.setString(2, "%"+search+"%");
				if(sort.needOrder()){
					stmt.setString(3, sort.getColumn());
					stmt.setString(4, sort.getColumn());
					stmt.setInt(5, offset);
					stmt.setInt(6, scope);
				} else {

					stmt.setInt(3, offset);
					stmt.setInt(4, scope);
				}
			}
			else{
				if(sort.needOrder()) { query.append(" ORDER BY ? ?"); }
				query.append(" LIMIT ?,?") ;
				stmt = conn.prepareStatement(query.toString());
				if(sort.needOrder()){
					stmt.setString(1, sort.getColumn());
					stmt.setString(2, sort.getColumn());
					stmt.setInt(3, offset);
					stmt.setInt(4, scope);
				} else {

					stmt.setInt(1, offset);
					stmt.setInt(2, scope);
				}
			}
			results = stmt.executeQuery();
			while(results.next()) {
				if (results != null)
					try {
						Computer cp = new Computer();
						
						cp.setId(new Integer(results.getInt(1)));
						cp.setName(results.getString(2));
						cp.setIntroduced(ComputerMapper.stringToCalendar(results
								.getString(3)));
						cp.setDiscontinued(ComputerMapper.stringToCalendar(results
								.getString(4)));

						Company comp = new Company();

						comp.setId(results.getInt(5));
						comp.setName(results.getString(7));
						cp.setCompany(comp);
						liste.add(cp);

					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeResultSet(results);
			ConnectionManager.closeStatement(stmt);
		}
		return liste;

	}

	public int getNumberWithCriteria(String search) {

		Connection conn = ConnectionManager.getConnectionThLocal();
		PreparedStatement stmt = null;
		ResultSet results = null;
		int resultInt = 0;
		StringBuilder query = new StringBuilder(80);
		query.append("SELECT COUNT(*) FROM computer LEFT OUTER JOIN company ON computer.company_id = company.id");
		try {
			if(search != null && !("".equals(search))) {
				query.append(" WHERE computer.name LIKE ? OR company.name LIKE ?");
				stmt = conn.prepareStatement(query.toString());
				stmt.setString(1, "%"+search+"%");
				stmt.setString(2, "%"+search+"%");
			}
			else{
				stmt = conn.prepareStatement(query.toString());
			}
			results = stmt.executeQuery();
			results.next();
			if (results != null)
				try {
					resultInt = results.getInt(1);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeResultSet(results);
			ConnectionManager.closeStatement(stmt);
		}
		return resultInt;
	}

	private void request(String query, Computer comp, String request) {

		PreparedStatement stmt = null;
		try {
			stmt = ConnectionManager.getConnectionThLocal().prepareStatement(
					query);
			if ("update".equals(request)) {
				stmt.setString(1, comp.getName());
				stmt.setDate(2, (Date) comp.getIntroduced().getTime());
				stmt.setDate(3, (Date) comp.getDiscontinued().getTime());
				stmt.setInt(4, comp.getCompany().getId());
				stmt.setInt(5, comp.getCompany().getId());
				stmt.executeUpdate(query);
			}
			if ("delete".equals(request)) {
				stmt.setInt(1, comp.getCompany().getId());
			}
			if ("insert".equals(request)) {
				stmt.setString(1, comp.getName());
				stmt.setDate(2, new Date(comp.getIntroduced().getTime().getTime()));
				stmt.setDate(3, new Date(comp.getDiscontinued().getTime().getTime()));
				stmt.setInt(4, comp.getCompany().getId());

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeStatement(stmt);
		}

	}

	private List<Computer> select(String query) {

		List<Computer> liste = new ArrayList<Computer>();
		Connection conn = ConnectionManager.getConnectionThLocal();
		PreparedStatement prep = null;
		ResultSet results = null;
		try {
			prep = conn.prepareStatement(query);

			results = prep.executeQuery();
			while (results.next()) {
				Computer cp = new Computer();

				cp.setId(new Integer(results.getInt(1)));
				cp.setName(results.getString(2));
				cp.setIntroduced(ComputerMapper.stringToCalendar(results
						.getString(3)));
				cp.setDiscontinued(ComputerMapper.stringToCalendar(results
						.getString(4)));

				Company comp = new Company();

				comp.setId(results.getInt(5));
				comp.setName(results.getString(7));
				cp.setCompany(comp);

				liste.add(cp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeResultSet(results);
			ConnectionManager.closeStatement(prep);
		}
		return liste;
	}

}
