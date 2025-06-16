package com.cgi.scrumescapegame.enemyattacks;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Randomizer;
import com.cgi.scrumescapegame.enemies.Enemy;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.diogonunes.jcolor.Attribute;

public class MissAlignment_ForcedAlignment implements AttackBehavior{
    public String getName() {
        return "Forced Alignment";
    }

    public int attack(Enemy enemy, Player player) {
        int heal = Randomizer.getRandomInt(5, 10);
        int dmg = Randomizer.getRandomInt(5, 10);
        player.changeHp(-dmg);
        enemy.changeHp(heal);
        PrintMethods.printlnColor(enemy + " healde " + heal + " HP!", Attribute.BRIGHT_RED_TEXT());
        return dmg;
    }
}
