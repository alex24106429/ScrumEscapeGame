package com.cgi.scrumescapegame.enemyattacks;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Randomizer;
import com.cgi.scrumescapegame.enemies.Enemy;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.diogonunes.jcolor.Attribute;

public class MissAlignment_DefinitionofHealed implements AttackBehavior {
    public String getName() {
        return "Definition of Healed";
    }

    public int attack(Enemy enemy, Player player) {
        int heal = Randomizer.getRandomInt(5, 20);
        enemy.changeHp(heal);
        PrintMethods.printlnColor(enemy.getName() + " healde " + heal + " HP!", Attribute.BRIGHT_RED_TEXT());
        return 0;
    }
}
