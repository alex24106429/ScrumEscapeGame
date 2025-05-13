package com.cgi.scrumescapegame;

import java.sql.SQLException;

import org.h2.tools.Console;

public class ScrumEscapeGame {

	private static void startH2Console() {
        new Thread(() -> {
            try {
                Console.main(new String[]{"-web"});
            } catch (SQLException e) {
                System.err.println("Error starting H2 console: " + e.getMessage());
            }
        }).start();
    }

    public static void main(String[] args) {
        Game game = new Game();
        if(Game.debug) startH2Console();
        game.start();
    }
}