package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Difficulty;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Room;

public class KamerScrumboard extends Room {
    public KamerScrumboard(int roomX, int roomY) {
        super("Scrumboard kamer", "Je bent in de Scrumboard kamer. Hier kan je zien welke taken en user stories er nog aan gewerkt moeten worden.", roomX, roomY);
    }

    @Override
    public void roomLogic(Player player, Difficulty difficulty) {
        // Specifieke acties voor de Scrumboardkamer
    }
}