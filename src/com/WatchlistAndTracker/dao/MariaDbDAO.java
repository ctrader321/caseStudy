package com.WatchlistAndTracker.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class MariaDbDAO {

	private static String persistenceUnitName = "RankingGames";

	private static final String DATABASE_NAME = "tracker_and_watchlist";
	private static Connection connection;
	private static Statement statement;
	
	
	protected static EntityManagerFactory emf = null;
	protected static EntityManager em = null;

	protected static void connect() {
		emf = Persistence.createEntityManagerFactory(persistenceUnitName);
		em = emf.createEntityManager();
	}

	protected static void dispose() {

		em.close();
		emf.close();

	}

	public static boolean runSQLFile(String filePath) {
		try {
			connect();
			try (BufferedReader bufferedReader = new BufferedReader(new FileReader("./resources/sql/" + filePath))) {
				StringBuffer query = new StringBuffer();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					query.append(line);
					if (line.contains(";")) {
						em.getTransaction().begin();
						em.createNativeQuery(query.toString()).executeUpdate();
						em.getTransaction().commit();
						query = new StringBuffer();
					}
				}
				return true;
			} catch (Exception e) {
				// e.printStackTrace();
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
		dispose();
		return false;
	}

	public static boolean createTables() {
		boolean ret = false;
		try {
			connect();
			em.getTransaction().begin();
			em.getTransaction().commit();
			ret = true;
		} catch (Exception e) {
			// e.printStackTrace();
		}
		dispose();
		return ret;
	}

	// JDBC methods

	public static boolean startJDBC(String user, String pass) {
		try { 
			String dbUrl = null;
			dbUrl = "jdbc:mariadb://localhost:3306/"; // maria DB
			connection = DriverManager.getConnection(dbUrl, user, pass);
			return true;
		} catch(Exception e) {
			System.out.println("Please make sure you have configured the persistence.xml file with");
			System.out.println("your database details and that your database program is running.");
		}
		return false;
	}

	public static boolean stopJDBC() {
		try {
			statement.close();
			connection.close();
			return true;
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return false;
	}

	public static boolean createDatabase() {
		try {
			statement = connection.createStatement();
			statement.execute("CREATE database IF NOT EXISTS " + DATABASE_NAME);
			statement.execute("USE " + DATABASE_NAME);
			return true;
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return false;
	}

	public static boolean dropDatabase() {
		try {
			statement = connection.createStatement();
			statement.execute("DROP database IF EXISTS " + DATABASE_NAME);
			return true;
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return false;
	}
}