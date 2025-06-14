package com.cgi.scrumescapegame.enemyattacks;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Randomizer;
import com.cgi.scrumescapegame.enemies.Enemy;

public class BacklogHydra_DeadlineDoom implements AttackBehavior {

	public String getName() {
		return "Deadline Doom";
	}

	public int attack(Enemy enemy, Player player) {
		int dmg = Randomizer.getRandomInt(20, 30);
		player.changeHp(-dmg);
		return dmg;
	}
}