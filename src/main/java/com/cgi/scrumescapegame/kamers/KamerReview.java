package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Room;

public class KamerReview extends Room {
    public KamerReview() {
        super("Kamer Review", "Welkom in de Sprint Review kamer. Hier presenteer je het werkende product aan de stakeholders.");
    }

    @Override
    public void enterRoom(Player player) {
        super.enterRoom(player);
        // Specifieke acties voor de review kamer
    }
}