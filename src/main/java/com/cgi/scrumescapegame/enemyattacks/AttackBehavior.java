package com.cgi.scrumescapegame.enemyattacks;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.enemies.Enemy;

public interface AttackBehavior {
	String getName();

	int attack(Enemy enemy, Player player);
}