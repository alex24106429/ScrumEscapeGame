package com.cgi.scrumescapegame.enemyattacks;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.enemies.Enemy;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.diogonunes.jcolor.Attribute;

public class SprintSnipper_CrabbyCritique implements AttackBehavior {
    public String getName() {
        return "Crabby Critique";
    }

    public int attack(Enemy enemy, Player player) {
        PrintMethods.printlnColor("Sprint Snipper zegt: Lef dat je dit committed hebt.", Attribute.BRIGHT_YELLOW_TEXT());
        return 0;
    }
}
