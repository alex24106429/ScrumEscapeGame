package com.cgi.scrumescapegame.enemyattacks;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.enemies.AttackBehavior;
import com.cgi.scrumescapegame.enemies.Enemy;

import java.util.Random;

public class BacklogHydra_QueueQuake implements AttackBehavior {
	private Random rand = new Random();

	public String getName() {
		return "Queue Quake";
	}

	public int attack(Enemy enemy, Player player) {
		int base = 30;
		int var = base / 5;
		int dmg = base - var + rand.nextInt(var * 2 + 1);
		player.changeHp( - dmg);
		return dmg;
	}
}