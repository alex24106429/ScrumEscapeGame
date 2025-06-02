package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Difficulty;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Room;

public class StartKamer extends Room {
    public StartKamer(int roomX, int roomY) {
        super("De Startkamer", "Welkom, dappere avonturier! Je staat aan het begin van je Scrum-reis. Typ 'help' voor commando's.", roomX, roomY);
    }

    @Override
    public void roomLogic(Player player, Difficulty difficulty) {
        setCleared(true);
    }
}