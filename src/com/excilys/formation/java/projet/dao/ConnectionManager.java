package com.excilys.formation.java.projet.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

@Component("connectionManager")
public class ConnectionManager {

	private static BoneCP connectionPool = null;
	private static ThreadLocal<Connection> thLocal = new ThreadLocal<Connection>();



	private static void configureConnPool() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull");
			config.setUsername("root");
			config.setPassword("root");
			config.setMinConnectionsPerPartition(5);
			config.setPartitionCount(2); 
			connectionPool = new BoneCP(config);
			System.out
					.println("contextInitialized.....Connection Pooling is configured");
			System.out.println("Total connections ==> "
					+ connectionPool.getTotalCreatedConnections());
			ConnectionManager.setConnectionPool(connectionPool);

		} catch (Exception e) {
			e.printStackTrace(); 
		}

	}

	public static void shutdownConnPool() {

		try {
			BoneCP connectionPool = ConnectionManager.getConnectionPool();
			System.out.println("contextDestroyed....");
			if (connectionPool != null) {
				connectionPool.shutdown();
				System.out
						.println("contextDestroyed.....Connection Pooling shut downed!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {

		Connection conn = null;
		try {
			conn = getConnectionPool().getConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;

	}

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

	public static void closeConnection(Connection conn) {

		try {
			thLocal.get().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static BoneCP getConnectionPool() {
		if (connectionPool == null) {
			System.out.println("connection pool nnulll");
			configureConnPool();
		}
		return connectionPool;
	}

	public static void setConnectionPool(BoneCP connectionPool) {
		ConnectionManager.connectionPool = connectionPool;
	}

	public static void setConnectionThLocal() {
		thLocal.set(getConnection());
	}

	public static Connection getConnectionThLocal() {
		return thLocal.get();
	}

	public static void closeConnectionThLocal() {
		
		thLocal.remove();
	}

	public static void deconnection() {
		try{
		getConnectionThLocal().commit();
		getConnectionThLocal().setAutoCommit(true);
		} catch (SQLException e) {
			try {
				getConnectionThLocal().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		closeConnectionThLocal();
		
	}

	public static void connection() {
		System.out.println("connection manager connection");
		setConnectionThLocal();
		try {
			getConnectionThLocal().setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
