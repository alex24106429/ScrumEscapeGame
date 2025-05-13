package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Room;


public class KamerDailyStandup extends Room {
    public KamerDailyStandup(int roomX, int roomY) {
        super("Kamer Daily Standup", "Je bent in de Daily Standup kamer. Hier wordt de voortgang van de sprint besproken. Wat ga je doen?", roomX, roomY);
    }
    @Override
    public void enterRoom(Player player) {
        super.enterRoom(player);
        // Specifieke acties voor de planningskamer
    }
}
