package com.excilys.formation.java.projet.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.java.projet.common.Sort;
import com.excilys.formation.java.projet.modele.*;
import com.excilys.formation.java.projet.dao.*;
import com.jolbox.bonecp.BoneCPDataSource;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("computerDao")
public class ComputerDAOImpl implements ComputerDAO{
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void init(BoneCPDataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}


	public void insert(Computer comp) {
		String query = "INSERT INTO computer (name, introduced, discontinued, company_id) "
				+ "VALUES (?,?,?,?); ";
		System.out.println(comp);
		List<String> list = new ArrayList<String>();
		list.add(comp.getName());
		if(comp.getIntroduced()!=null){
			list.add(comp.getIntroduced().toString());
		} else {
			list.add(null);
		} 
		if(comp.getDiscontinued()!=null){
			list.add(comp.getDiscontinued().toString());
		} else {
			list.add(null);
		}
		Integer id;
		if(comp.getCompany().getId()>0) {
			id = comp.getCompany().getId();
		} else {
			id = (Integer) null;
		}
		this.jdbcTemplate.update(query, list.toArray() , id);
	}

	public void delete(Computer comp) {

		String query = "DELETE FROM computer WHERE id= ?";
		this.jdbcTemplate.update(query, comp.getId());
	}

	public void update(Computer comp) {
		String query = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
		List<String> list = new ArrayList<String>();
		list.add(comp.getName());
		if(comp.getIntroduced()!=null){
			list.add(comp.getIntroduced().toString());
		} else {
			list.add(null);
		} 
		if(comp.getDiscontinued()!=null){
			list.add(comp.getDiscontinued().toString());
		} else {
			list.add(null);
		}
		Integer id_comp;
		if(comp.getCompany().getId()>0) {
			id_comp = comp.getCompany().getId();
		} else {
			id_comp = (Integer) null;
		}
		Integer id;
		if(comp.getId()>0) {
			id = comp.getId();
		} else {
			id = (Integer) null;
		}
		this.jdbcTemplate.update(query, id_comp, id);
	}

	public List<Computer> getAll() {
		String query = "SELECT * FROM computer LEFT OUTER JOIN company ON computer.company_id = company.id";
		return this.jdbcTemplate.query(query, new ComputerMapper() );
	}

	public List<Computer> getCriteria(String search, Sort sort, int offset, int scope) {

		if(sort == null){
			sort = new Sort();
			sort.setColumn(1);
			sort.setOrder("ASC");
		}
		StringBuilder query = new StringBuilder(80);
		query.append("SELECT * FROM computer LEFT OUTER JOIN company ON computer.company_id = company.id");
		List<Computer> liste;
		if(search != null && !("".equals(search))) {
			query.append(" WHERE computer.name LIKE ? OR company.name LIKE ? ORDER BY ").append(sort.toString()).append(" LIMIT ?,?");
			liste = jdbcTemplate.query(query.toString(), new ComputerMapper(),"%"+search+"%","%"+search+"%", offset, scope);
		}
		else{
			query.append(" ORDER BY ").append(sort.toString()).append(" LIMIT ?,?");
			liste = jdbcTemplate.query(query.toString(), new ComputerMapper(), offset, scope);
		}
		return liste;

	}

	public int getNumberWithCriteria(String search) {

		int resultInt = 0;
		StringBuilder query = new StringBuilder(80);
		query.append("SELECT COUNT(*) FROM computer LEFT OUTER JOIN company ON computer.company_id = company.id");
		if(search != null && !("".equals(search))) {
			query.append(" WHERE computer.name LIKE ? OR company.name LIKE ?");
			resultInt = this.jdbcTemplate.queryForObject(query.toString(), Integer.class, "%"+search+"%", "%"+search+"%");
		}
		else{
			resultInt = this.jdbcTemplate.queryForObject(query.toString(), Integer.class);
		}
		return resultInt;
	}

	private static final class ComputerMapper implements RowMapper<Computer> {

		public Computer mapRow(ResultSet results, int rowNum) throws SQLException {
			Computer cp = new Computer();

			cp.setId(new Integer(results.getInt(1)));
			cp.setName(results.getString(2));
			if(results.getString(3) != null) {
				cp.setIntroduced(new DateTime(results.getString(3).substring(0,10)));
			}
			if(results.getString(4) != null) {
				cp.setDiscontinued(new DateTime(results.getString(4).substring(0,10)));
			}

			Company comp = new Company();

			comp.setId(results.getInt(5));
			comp.setName(results.getString(7));
			cp.setCompany(comp);

			return cp;
		}
	}

}



