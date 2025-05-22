package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.diogonunes.jcolor.Attribute;

public class Book implements UsableItem {
	public String getName() {
		return "Kamer Info Boek";
	}

	public String getDescription() {
		return "Geeft informatie over de kamer.";
	}

	public String getImagepath() {
		return "items/book.png";
	}

	public void useItem(Player player) {
		PrintMethods.printlnColor(player.getCurrentRoom().getDescription(), Attribute.BRIGHT_YELLOW_TEXT());
	}

    public int getUsesLeft() {
        return Integer.MAX_VALUE;
    }
}
