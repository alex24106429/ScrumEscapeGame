package com.cgi.scrumescapegame.enemyattacks;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Randomizer;
import com.cgi.scrumescapegame.enemies.Enemy;

public class CodeGolem_StandupStun implements AttackBehavior {

    public String getName() {
        return "Standup Stun";
    }

    public int attack(Enemy enemy, Player player) {
        int dmg = Randomizer.getRandomInt(2, 15);
        player.changeHp(-dmg);
        return dmg;
    }
}
