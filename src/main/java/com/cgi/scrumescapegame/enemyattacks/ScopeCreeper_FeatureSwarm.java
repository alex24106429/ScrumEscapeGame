package com.cgi.scrumescapegame.enemyattacks;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Randomizer;
import com.cgi.scrumescapegame.enemies.Enemy;

public class ScopeCreeper_FeatureSwarm implements AttackBehavior {

	public String getName() {
		return "Feature Swarm";
	}

	public int attack(Enemy enemy, Player player) {
		return player.changeHp(-Randomizer.getRandomInt(15, 40));
	}
}