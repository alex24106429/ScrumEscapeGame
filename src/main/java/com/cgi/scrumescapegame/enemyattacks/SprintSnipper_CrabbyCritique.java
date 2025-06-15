package com.cgi.scrumescapegame.enemyattacks;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.enemies.Enemy;

public class SprintSnipper_CrabbyCritique implements AttackBehavior {
    public String getName() {
        return "Crabby Critique";
    }

    public int attack(Enemy enemy, Player player) {
        System.out.println("Lef dat je dit committed hebt");
        return 0;
    }
}
