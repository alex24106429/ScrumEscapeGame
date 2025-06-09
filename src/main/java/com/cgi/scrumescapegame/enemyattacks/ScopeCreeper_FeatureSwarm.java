package com.cgi.scrumescapegame.enemyattacks;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.enemies.AttackBehavior;
import com.cgi.scrumescapegame.enemies.Enemy;

import java.util.Random;

public class ScopeCreeper_FeatureSwarm implements AttackBehavior {
	private Random rand = new Random();

	public String getName() {
		return "Feature Swarm";
	}

	public int attack(Enemy enemy, Player player) {
		int base = 50;
		int var = base / 4;
		int dmg = base - var + rand.nextInt(var * 2 + 1);
		player.changeHp( - dmg);
		return dmg;
	}
}