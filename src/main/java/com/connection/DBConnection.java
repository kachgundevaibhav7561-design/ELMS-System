package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	private static final String URL = "jdbc:mysql://localhost:3306/elms_db";
	private static final String USER = System.getenv("DB_USER");
	private static final String PASSWORD = System.getenv("DB_PASSWORD");

	public static Connection getConnection() {

		Connection con = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(URL, USER, PASSWORD);

			System.out.println("Database Connected Successfully");

		} catch (Exception e) {

			System.out.println(e);
		}

		return con;
	}
}