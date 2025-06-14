package com.cgi.scrumescapegame.enemyattacks;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Randomizer;
import com.cgi.scrumescapegame.enemies.Enemy;

public class SlimeMaster_SprintSnare implements AttackBehavior {
    public String getName() {
        return "Sprint Snare";
    }

    public int attack(Enemy enemy, Player player) {
        int dmg = Randomizer.getRandomInt(15, 20);
        player.changeHp(-dmg);
        return dmg;
    }
}