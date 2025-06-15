package com.cgi.scrumescapegame.enemyattacks;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Randomizer;
import com.cgi.scrumescapegame.enemies.Enemy;

public class MissAlignment_BacklogBombardment {
    public String getName() {
        return "Backlog Bombardment";
    }

    public int attack(Enemy enemy, Player player) {
        int dmg = Randomizer.getRandomInt(25, 35);
        player.changeHp(-dmg);
        return dmg;
    }
}
