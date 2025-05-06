package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Room;

public class KamerPlanning extends Room {
    public KamerPlanning() {
        super("Kamer Planning", "Je bent in de Planning Poker kamer. Hier wordt de scope van de sprint bepaald. Wat ga je doen?");
    }

    @Override
    public void enterRoom(Player player) {
        super.enterRoom(player);
        // Specifieke acties voor de planningskamer
    }
}