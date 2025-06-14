package com.cgi.scrumescapegame.enemyattacks;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Randomizer;
import com.cgi.scrumescapegame.enemies.Enemy;

public class BacklogHydra_QueueQuake implements AttackBehavior {

	public String getName() {
		return "Queue Quake";
	}

	public int attack(Enemy enemy, Player player) {
		int dmg = Randomizer.getRandomInt(10, 15);
		player.changeHp(-dmg);
		return dmg;
	}
}