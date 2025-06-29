package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.diogonunes.jcolor.Attribute;

public class Book extends Item implements UsableItem {
    @Override
    public String getName() {
        return "Kamer Info Boek";
    }

    @Override
    public String getDescription() {
        return "Geeft informatie over de kamer.";
    }

    @Override
    public String getImagepath() {
        return "items/book.png";
    }

    @Override
    public void useItem(Player player) {
        PrintMethods.printlnColor(player.getCurrentRoom().getDescription(), Attribute.BRIGHT_YELLOW_TEXT());
    }

    public Book() {
        super(0);
    }
}