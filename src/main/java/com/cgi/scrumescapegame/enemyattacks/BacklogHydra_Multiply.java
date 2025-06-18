package com.cgi.scrumescapegame.enemyattacks;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Randomizer;
import com.cgi.scrumescapegame.enemies.Enemy;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.diogonunes.jcolor.Attribute;

public class BacklogHydra_Multiply implements AttackBehavior {

	public String getName() {
		return "Multiply";
	}

	public int attack(Enemy enemy, Player player) {
		int heal = Randomizer.getRandomInt(5, 35);
		enemy.changeHp(heal);
		PrintMethods.printlnColor("Backlog Hydra healde " + heal + " HP!", Attribute.BRIGHT_RED_TEXT());
		return 0;
	}
}