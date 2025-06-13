package com.cgi.scrumescapegame.enemyattacks;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Randomizer;
import com.cgi.scrumescapegame.enemies.AttackBehavior;
import com.cgi.scrumescapegame.enemies.Enemy;

public class CodeGolem_StackOverflowStomp implements AttackBehavior {

    public String getName() {
        return "Stack Overflow Stomp";
    }

    public int attack(Enemy enemy, Player player) {
        int base = 10;
        int var = base / 5;
        int dmg = base - var + Randomizer.getRandomInt(var * 2 + 1);
        player.changeHp( - dmg);
        return dmg;
    }
}
