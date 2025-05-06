package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Room;

public class StartKamer extends Room {
    public StartKamer() {
        super("De Startkamer", "Welkom, dappere avonturier! Je staat aan het begin van je Scrum-reis. Typ 'help' voor commando's.");
    }

    @Override
    public void enterRoom(Player player) {
        super.enterRoom(player);
        // Specifieke acties voor de startkamer kunnen hier komen
    }
}