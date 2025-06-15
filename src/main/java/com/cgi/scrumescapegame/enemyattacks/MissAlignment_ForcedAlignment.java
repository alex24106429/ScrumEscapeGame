package com.cgi.scrumescapegame.enemyattacks;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Randomizer;
import com.cgi.scrumescapegame.enemies.Enemy;

public class MissAlignment_ForcedAlignment {
    public String getName() {
        return "Forced Alignment";
    }

    public int attack(Enemy enemy, Player player) {
        int heal = Randomizer.getRandomInt(5, 10);
        int dmg = Randomizer.getRandomInt(5, 10);
        player.changeHp(-dmg);
        enemy.changeHp(heal);
        System.out.println(enemy + " healde " + heal + " HP!");
        return dmg;
    }
}
