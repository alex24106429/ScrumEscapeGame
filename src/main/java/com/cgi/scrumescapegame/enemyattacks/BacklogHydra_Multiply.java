package com.cgi.scrumescapegame.enemyattacks;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Randomizer;
import com.cgi.scrumescapegame.enemies.Enemy;

public class BacklogHydra_Multiply implements AttackBehavior {

	public String getName() {
		return "Multiply";
	}

	public int attack(Enemy enemy, Player player) {
		enemy.changeHp(Randomizer.getRandomInt(1, 35));
		return 0;
	}
}