package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Item;
import com.cgi.scrumescapegame.Player;

public class HealingPotion implements Item {
	public String getName() {
		return "Healing Potion";
	}

	public String getDescription() {
		return "Healing Potion";
	}

	public String getImagepath() {
		return "items/healingpotion.png";
	}

	public void useItem(Player player) {
		player.gainLife();
	}
}
