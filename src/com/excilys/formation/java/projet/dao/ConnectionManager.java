package com.excilys.formation.java.projet.dao;

import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.stereotype.Component;

@Component("connectionManager")
public class ConnectionManager {

	
	public static void closeStatement(Statement stmt) {
		try {
			if (stmt != null)
				stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void closeResultSet(ResultSet rSet) {
		try {
			if (rSet != null)
				rSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	
}
