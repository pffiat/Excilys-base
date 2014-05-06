package com.excilys.formation.java.projet.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.excilys.formation.java.projet.dao.*;

import com.excilys.formation.java.projet.modele.*;

public enum CompanyDAOImpl implements CompanyDAO {

	INSTANCE;

	private CompanyDAOImpl() {

	}

	public static CompanyDAOImpl getInstance() {
		return INSTANCE;
	}

	public List<Company> getAll() {	
		
		List<Company> liste = new ArrayList<Company>();
		PreparedStatement stmt = null;
		ResultSet results = null;
		try {
			stmt = ConnectionManager.getConnectionThLocal().prepareStatement("SELECT * FROM company");			
			results = stmt.executeQuery();
			while (results.next()) {
				Company cp = new Company();
				cp.setId(new Integer(results.getInt(1)));
				cp.setName(results.getString(2));
				liste.add(cp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liste;
	}

}
