package com.cgi.scrumescapegame.tests;

import com.cgi.scrumescapegame.Difficulty;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.kamers.StartKamer;

public class MockRoomTest {

    public static void main(String[] args) {
        Player player = new Player();
        StartKamer room = new StartKamer(0, 0);
        player.setCurrentRoom(room);
        room.enterRoom(player, Difficulty.NORMAL);

        if (room.getCleared()) {
            System.out.println("✅ setCleared() werd succesvol aangeroepen");
        } else {
            System.out.println("❌ setCleared() werd NIET aangeroepen");
        }
    }
}
