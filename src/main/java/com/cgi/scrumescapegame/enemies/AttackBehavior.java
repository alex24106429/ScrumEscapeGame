package com.cgi.scrumescapegame.enemies;

import com.cgi.scrumescapegame.Player;

public interface AttackBehavior {
	String getName();

	int attack(Enemy enemy, Player player);
}