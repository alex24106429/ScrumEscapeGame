package com.cgi.scrumescapegame.enemyattacks;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Randomizer;
import com.cgi.scrumescapegame.enemies.Enemy;

public class MissAlignment_BacklogBombardment implements AttackBehavior{
    public String getName() {
        return "Backlog Bombardment";
    }

    public int attack(Enemy enemy, Player player) {
		return player.changeHp(-Randomizer.getRandomInt(25, 50));
    }
}
