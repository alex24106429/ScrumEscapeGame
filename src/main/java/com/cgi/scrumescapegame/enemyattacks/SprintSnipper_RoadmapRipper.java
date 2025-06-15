package com.cgi.scrumescapegame.enemyattacks;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Randomizer;
import com.cgi.scrumescapegame.enemies.Enemy;

public class SprintSnipper_RoadmapRipper implements AttackBehavior {
    public String getName() {
        return "Roadmap Ripper";
    }

    public int attack(Enemy enemy, Player player) {
        int dmg = Randomizer.getRandomInt(10, 20);
        player.changeHp(-dmg);
        return dmg;
    }
}
