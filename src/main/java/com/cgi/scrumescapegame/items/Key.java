package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.animations.DoorOpeningAnimation;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.cgi.scrumescapegame.kamers.Room;
import com.diogonunes.jcolor.Attribute;

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
        DoorOpeningAnimation.playAnimation();
        PrintMethods.printlnColor("De deuren in de kamer zijn geopend!", Attribute.BRIGHT_GREEN_TEXT());
    }

    @Override
    public int getUsesLeft() {
        return 0;
    }
}
