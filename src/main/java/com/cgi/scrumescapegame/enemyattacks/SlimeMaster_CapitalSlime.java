package com.cgi.scrumescapegame.enemyattacks;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Randomizer;
import com.cgi.scrumescapegame.enemies.Enemy;

public class SlimeMaster_CapitalSlime implements AttackBehavior {
    public String getName() {
        return "Capital Slime";
    }

    public int attack(Enemy enemy, Player player) {
        player.changeGold(-Randomizer.getRandomInt(7, 15));
        return 0;
    }
}