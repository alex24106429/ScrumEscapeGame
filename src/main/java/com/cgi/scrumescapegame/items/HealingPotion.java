package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Player;

public class HealingPotion {
	public String getName() {
		return "Healing Potion";
	}

	public String getDescription() {
		return "Healing Potion";
	}

	public String getImagePath() {
		return "items/healingpotion.png";
	}

	public void useItem(Player player) {
		player.gainLife();
	}
}
