package com.cgi.scrumescapegame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.Console;

public class DatabaseManager {
    public static final String jdbcUrl = "jdbc:h2:./scrumescapedb;USER=sa;PASSWORD=sa";

    public Connection conn;

    public DatabaseManager() {
        try {
            this.conn = DriverManager.getConnection(jdbcUrl);
            if(Game.debug) {
                System.out.println("Verbonden met de database!");
                startH2Console();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeQuery(String SQLQuery) {
        if(Game.debug) System.out.println("Executing SQL query: " + SQLQuery);
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(SQLQuery);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void startH2Console() {
        new Thread(() -> {
            try {
                Console.main(new String[]{"-web"});
            } catch (SQLException e) {
                System.err.println("Error starting H2 console: " + e.getMessage());
            }
        }).start();
    }

}
