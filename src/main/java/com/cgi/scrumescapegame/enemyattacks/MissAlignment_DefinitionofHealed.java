package com.cgi.scrumescapegame.enemyattacks;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Randomizer;
import com.cgi.scrumescapegame.enemies.Enemy;

public class MissAlignment_DefinitionofHealed implements AttackBehavior {
    public String getName() {
        return "Definition of Healed";
    }

    public int attack(Enemy enemy, Player player) {
        int heal = Randomizer.getRandomInt(5, 20);
        enemy.changeHp(heal);
        System.out.println(enemy + " healde " + heal + " HP!");
        return 0;
    }
}
