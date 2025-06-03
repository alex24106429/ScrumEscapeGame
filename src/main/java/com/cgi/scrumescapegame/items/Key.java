package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Room;

public class Key extends Item implements UsableItem, LimitedUseItem {
    @Override
    public String getName() {
        return "Sleutel";
    }

    @Override
    public String getDescription() {
        return "Opent alle deuren van de kamer.";
    }

    @Override
    public String getImagepath() {
        return "items/key.png";
    }

    public Key() {
        super(0);
    }

    @Override
    public void useItem(Player player) {
        Room currentRoom = player.getCurrentRoom();
        currentRoom.setCleared(true);
    }

    @Override
    public int getUsesLeft() {
        return 0;
    }
}
