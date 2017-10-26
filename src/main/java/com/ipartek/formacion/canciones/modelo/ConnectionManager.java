package com.ipartek.formacion.canciones.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private static String url = "jdbc:mysql://localhost:3306/spoty";
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String username = "root";
	private static String password = "";
	private static Connection con=null;

	public static Connection getConnection() {
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url, username, password);

		} catch (SQLException ex) {
			// log an exception. fro example:
			System.out.println("Failed to create the database connection.");
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			// log an exception. for example:
			System.out.println("Driver not found.");
			ex.printStackTrace();
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		return con;
	}

	public static void closeConnection() {
		try {
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			System.out.println("Error al cerrar conexion");
			e.printStackTrace();
		}

	}

}