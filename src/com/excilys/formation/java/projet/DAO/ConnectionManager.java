package com.excilys.formation.java.projet.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public enum ConnectionManager {
	INSTANCE;

	private ConnectionManager() {
	}

	public static ConnectionManager getInstance() {
		return INSTANCE;
	}

	private static BoneCP connectionPool = null;

	// public static void getInstance() {
	// System.out.println(cm);
	// if(cm == null) {
	// cm = new ConnectionManager();
	// configureConnPool();
	// }
	// System.out.println("ConnectionManager getInstance");
	// }


	private static void configureConnPool() {
		System.out.println("entrée config");
		try {
			Class.forName("com.mysql.jdbc.Driver"); // also you need the MySQL
													// driver
			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull");
			config.setUsername("root");
			config.setPassword("root");
			config.setMinConnectionsPerPartition(5); // if you say 5 here, there
														// will be 10 connection
														// available
														// config.setMaxConnectionsPerPartition(10);
			config.setPartitionCount(2); // 2*5 = 10 connection will be
											// available
			// config.setLazyInit(true); //depends on the application usage you
			// should chose lazy or not
			// setting Lazy true means BoneCP won't open any connections before
			// you request a one from it.
			connectionPool = new BoneCP(config); // setup the connection pool
			System.out
					.println("contextInitialized.....Connection Pooling is configured");
			System.out.println("Total connections ==> "
					+ connectionPool.getTotalCreatedConnections());
			ConnectionManager.setConnectionPool(connectionPool);

		} catch (Exception e) {
			e.printStackTrace(); // you should use exception wrapping on
									// real-production code
		}

	}

	public static void shutdownConnPool() {

		try {
			BoneCP connectionPool = ConnectionManager.getConnectionPool();
			System.out.println("contextDestroyed....");
			if (connectionPool != null) {
				connectionPool.shutdown(); // this method must be called only
											// once when the application stops.
				// you don't need to call it every time when you get a
				// connection from the Connection Pool
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
			// will get a thread-safe connection from the BoneCP connection
			// pool.
			// synchronization of the method will be done inside BoneCP source

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
			if (conn != null)
				conn.close(); // release the connection
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static BoneCP getConnectionPool() {
		if( connectionPool ==null){
			System.out.println("dans le if");
			configureConnPool();
		}
		return connectionPool;
	}

	public static void setConnectionPool(BoneCP connectionPool) {
		ConnectionManager.connectionPool = connectionPool;
	}
}
