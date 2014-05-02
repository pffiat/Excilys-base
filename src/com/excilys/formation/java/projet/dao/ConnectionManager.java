package com.excilys.formation.java.projet.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public enum ConnectionManager {
	INSTANCE;

	private static BoneCP connectionPool = null;
	private static ThreadLocal<Connection> thLocal = new ThreadLocal<Connection>();

	private ConnectionManager() {
		configureConnPool();
	}

	public static ConnectionManager getInstance() {
		return INSTANCE;
	}

	private static void configureConnPool() {
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
		// try {
		// if (conn != null)
		// conn.close(); // release the connection
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }

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
		setConnectionThLocal();
		try {
			getConnectionThLocal().setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
