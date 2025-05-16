package com.cgi.scrumescapegame.items;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Weapon;

public class Sword implements Weapon {	
	public String getName() {
		return "Iron Sword";
	}

	public String getDescription() {
		return "+10 ATK";
	}

	public String getImagepath() {
		return "items/sword.png";
	}

	public int getAttackBonus() {
		return 10;
	}

	public void equip(Player player) {
		player.addAttackModifier(getAttackBonus());
	}

    public void unequip(Player player) {
		player.addAttackModifier( - getAttackBonus());
	}
}
