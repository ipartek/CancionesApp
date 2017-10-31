package com.ipartek.formacion.canciones.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager implements AutoCloseable {

	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/spoty";
	public static final String USUARIO = "root";
	public static final String PASSWORD = "";

	private static Connection con;

	public static Connection open() throws ClassNotFoundException, SQLException {

		Class.forName(DRIVER);

		Connection con = DriverManager.getConnection(URL, USUARIO, PASSWORD);
		return con;
	}

	@Override
	public void close() throws SQLException {

		if (con != null) {
			con.close();
		}

	}

}
