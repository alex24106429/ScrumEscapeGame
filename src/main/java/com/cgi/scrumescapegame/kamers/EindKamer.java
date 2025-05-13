package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Room;

public class EindKamer extends Room {
    public EindKamer(int roomX, int roomY) {
        super("Eind Kamer", "Welkom bij de laatste kamer van het spel. We gaan je op je TIA testen. Wat ga je doen?", roomX, roomY);
    }

    @Override
    public void enterRoom(Player player) {
        super.enterRoom(player);
        // Specifieke acties voor de planningskamer
    }
}
