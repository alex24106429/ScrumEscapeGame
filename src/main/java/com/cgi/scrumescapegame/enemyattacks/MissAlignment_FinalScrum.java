package com.cgi.scrumescapegame.enemyattacks;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Randomizer;
import com.cgi.scrumescapegame.enemies.Enemy;

public class MissAlignment_FinalScrum implements AttackBehavior {
    public String getName() {
        return "Final Scrum";
    }

    public int attack(Enemy enemy, Player player) {
        return player.changeHp(-Randomizer.getWeightedRandomInt(40, 100, 3.0));
    }
}
