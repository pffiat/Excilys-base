package com.excilys.formation.java.projet.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.java.projet.common.Sort;
import com.excilys.formation.java.projet.modele.*;
import com.excilys.formation.java.projet.dao.*;

import java.sql.Connection;

import org.joda.time.DateTime;
import org.springframework.asm.Type;
import org.springframework.stereotype.Repository;

@Repository("computerDao")
public class ComputerDAOImpl implements ComputerDAO{


	public void insert(Computer comp) {
		String query = "INSERT INTO computer (name, introduced, discontinued, company_id) "
				+ "VALUES (?,?,?,?); ";
		System.out.println(comp);
		request(query, comp, "insert");
	}

	public void delete(Computer comp) {

		String query = "DELETE FROM computer WHERE id= ?";
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

		if(sort == null){
			sort = new Sort();
			sort.setColumn(1);
			sort.setOrder("ASC");
		}
		List<Computer> liste = new ArrayList<Computer>();
		Connection conn = ConnectionManager.getConnectionThLocal();
		PreparedStatement stmt = null;
		ResultSet results = null;
		StringBuilder query = new StringBuilder(80);
		query.append("SELECT * FROM computer LEFT OUTER JOIN company ON computer.company_id = company.id");
		try {
			if(search != null && !("".equals(search))) {
				query.append(" WHERE computer.name LIKE ? OR company.name LIKE ? ORDER BY ") ;
				query.append(sort.toString());
				query.append(" LIMIT ?,?");
				stmt = conn.prepareStatement(query.toString());
				stmt.setString(1, "%"+search+"%");
				stmt.setString(2, "%"+search+"%");
				stmt.setInt(3, offset);
				stmt.setInt(4, scope);
			}
			else{
				query.append(" ORDER BY ") ;
				query.append(sort.toString());
				query.append(" LIMIT ?,?");
				stmt = conn.prepareStatement(query.toString());
				stmt.setInt(1, offset);
				stmt.setInt(2, scope);
			}
			System.out.println(stmt.toString());
			results = stmt.executeQuery();
			while(results.next()) {
				if (results != null)
					try {
						Computer cp = new Computer();

						cp.setId(new Integer(results.getInt(1)));
						cp.setName(results.getString(2));
						if(results.getString(3) != null) {
							cp.setIntroduced(new DateTime(results.getString(3).substring(0,10)));
						}
						if(results.getString(4) != null){
							cp.setDiscontinued(new DateTime(results.getString(4).substring(0,10)));
						}

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
		System.out.println(query);
		try {
			stmt = ConnectionManager.getConnectionThLocal().prepareStatement(
					query);
			if ("update".equals(request)) {
				stmt.setString(1, comp.getName());
				if(comp.getIntroduced()!=null){
					stmt.setString(2, comp.getIntroduced().toString());
				} else {
					stmt.setString(2, null);
				}
				if(comp.getDiscontinued()!=null){
					stmt.setString(3, comp.getDiscontinued().toString());
				} else {
					stmt.setString(3, null);
				}
				if(comp.getCompany().getId()>0) {
					stmt.setInt(4, comp.getCompany().getId());
				} else {
					stmt.setNull(4, Type.INT);
				}
				stmt.setString(5, comp.getCompany().getName());
				stmt.executeUpdate();
			}
			if ("delete".equals(request)) {
				stmt.setInt(1, comp.getId());
				stmt.executeUpdate();
			}
			if ("insert".equals(request)) {
				stmt.setString(1, comp.getName());
				if(comp.getIntroduced()!=null){
					stmt.setString(2, comp.getIntroduced().toString());
				} else {
					stmt.setString(2, null);
				}
				if(comp.getDiscontinued()!=null){
					stmt.setString(3, comp.getDiscontinued().toString());
				} else {
					stmt.setString(3, null);
				}
				if(comp.getCompany().getId()>0) {
					stmt.setInt(4, comp.getCompany().getId());
				} else {
					stmt.setNull(4, Type.INT);
				}
				stmt.executeUpdate();
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
				cp.setIntroduced(new DateTime(results.getString(3)));
				cp.setDiscontinued(new DateTime(results.getString(4)));

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
