package fr.upec.sm.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/db_school";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "";

	private static Connection conn;

	public static Connection getConnection() {
		if (conn != null)
			return conn;
		try {
			Class.forName(DB_DRIVER);
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	// fermeture de la connexion
	public static void closeConnection(Connection toBeClosed) {
		if (toBeClosed == null)
			return;
		try {
			toBeClosed.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
