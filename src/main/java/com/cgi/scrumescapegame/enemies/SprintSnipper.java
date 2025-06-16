package com.cgi.scrumescapegame.enemies;

import com.cgi.scrumescapegame.enemyattacks.SprintSnipper_RoadmapRipper;
import com.cgi.scrumescapegame.enemyattacks.SprintSnipper_CrabbyCritique;
import com.cgi.scrumescapegame.enemyattacks.SprintSnipper_ItemSnipper;

public class SprintSnipper extends Enemy {
    private static String name = "Sprint Snipper";
    private static String imagePath = "monsters/krab.png";
    private static int maxHp = 60;

    public SprintSnipper() {
        super(name, imagePath, maxHp);
        behaviors.add(new SprintSnipper_RoadmapRipper());
        behaviors.add(new SprintSnipper_CrabbyCritique());
        behaviors.add(new SprintSnipper_ItemSnipper());
    }
}
