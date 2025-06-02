package com.cgi.scrumescapegame.enemies;

import com.cgi.scrumescapegame.Player;
import java.util.Random;

public class NormalAttackBehavior implements AttackBehavior {
	private Random rand = new Random();

	public String getName() {
		return "Normal Attack";
	}

	public int attack(Enemy enemy, Player player) {
		int base = enemy.getAttack();
		int var = base / 5;
		int dmg = base - var + rand.nextInt(var * 2 + 1);
		player.loseHp(dmg);
		return dmg;
	}
}