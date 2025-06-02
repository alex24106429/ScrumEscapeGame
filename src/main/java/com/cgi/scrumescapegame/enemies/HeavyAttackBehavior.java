package com.cgi.scrumescapegame.enemies;

import com.cgi.scrumescapegame.Player;
import java.util.Random;

public class HeavyAttackBehavior implements AttackBehavior {
	private Random rand = new Random();

	public String getName() {
		return "Heavy Attack";
	}

	public int attack(Enemy enemy, Player player) {
		int base = enemy.getAttack() * 3 / 2;
		int var = base / 4;
		int dmg = base - var + rand.nextInt(var * 2 + 1);
		player.loseHp(dmg);
		return dmg;
	}
}