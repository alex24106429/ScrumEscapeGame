package com.cgi.scrumescapegame.enemyattacks;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Randomizer;
import com.cgi.scrumescapegame.enemies.Enemy;

public class CodeGolem_StackOverflowStomp implements AttackBehavior {

    public String getName() {
        return "Stack Overflow Stomp";
    }

    public int attack(Enemy enemy, Player player) {
		return player.changeHp(-Randomizer.getWeightedRandomInt(20, 35));

    }
}
