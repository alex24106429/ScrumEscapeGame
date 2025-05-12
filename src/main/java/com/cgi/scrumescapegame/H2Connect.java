package com.cgi.scrumescapegame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Connect {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:h2:./scrumescapedb;USER=sa;PASSWORD=sa";

		Connection conn = null;

		try {
			// Get the connection
			conn = DriverManager.getConnection(jdbcUrl);

			if (conn != null) {
				System.out.println("Connected to H2 database successfully!");
				// You can now use 'conn' to create statements and execute SQL
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 3. Close the connection
			if (conn != null) {
				try {
					conn.close();
					System.out.println("Connection closed.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}