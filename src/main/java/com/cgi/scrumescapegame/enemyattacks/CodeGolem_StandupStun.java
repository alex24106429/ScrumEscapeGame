package com.cgi.scrumescapegame.enemyattacks;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Randomizer;
import com.cgi.scrumescapegame.enemies.AttackBehavior;
import com.cgi.scrumescapegame.enemies.Enemy;

public class CodeGolem_StandupStun implements AttackBehavior {

    public String getName() {
        return "Standup Stun";
    }

    public int attack(Enemy enemy, Player player) {
        int base = 15;
        int var = base / 5;
        int dmg = base - var + Randomizer.getRandomInt(var * 2 + 1);
        player.changeHp( - dmg);
        return dmg;
    }
}
