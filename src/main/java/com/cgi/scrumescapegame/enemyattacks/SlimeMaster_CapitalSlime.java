package com.cgi.scrumescapegame.enemyattacks;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Randomizer;
import com.cgi.scrumescapegame.enemies.AttackBehavior;
import com.cgi.scrumescapegame.enemies.Enemy;

public class SlimeMaster_CapitalSlime implements AttackBehavior {
    public String getName() {
        return "Capital Slime";
    }

    public int attack(Enemy enemy, Player player) {
        int base = 10;
        int var = base / 2;
        int gold = base - var + Randomizer.getRandomInt(var * 2 + 1);
        player.changeGold(gold);
        return 0;
    }
}