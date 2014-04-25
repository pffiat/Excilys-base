package com.excilys.formation.java.projet.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {

	public Connection conn = null;
	public ConnectionManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance(); //AJOUTER LE PAQUET
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void connection(/* do to */) {

		String url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
		try {
			conn = DriverManager.getConnection(url, "root", "root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void close(Statement stmt, ResultSet results ) {			
		try{
			if(stmt != null)
				stmt.close();
			if(conn != null)
				conn.close();
			if(results != null)
				results.close();
		} catch (SQLException e) {
			e.printStackTrace();	
		}

	}
}
