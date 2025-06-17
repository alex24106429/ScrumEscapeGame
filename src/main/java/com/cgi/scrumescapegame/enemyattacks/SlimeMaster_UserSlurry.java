package com.cgi.scrumescapegame.enemyattacks;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Randomizer;
import com.cgi.scrumescapegame.enemies.Enemy;

public class SlimeMaster_UserSlurry implements AttackBehavior {
    public String getName() {
        return "User Slurry";
    }

    public int attack(Enemy enemy, Player player) {
		return player.changeHp(-Randomizer.getRandomInt(20, 40));
    }
}
