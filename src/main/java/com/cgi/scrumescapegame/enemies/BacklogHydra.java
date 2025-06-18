package com.cgi.scrumescapegame.enemies;

import com.cgi.scrumescapegame.enemyattacks.BacklogHydra_DeadlineDoom;
import com.cgi.scrumescapegame.enemyattacks.BacklogHydra_Multiply;
import com.cgi.scrumescapegame.enemyattacks.BacklogHydra_QueueQuake;

public class BacklogHydra extends Enemy {
    private static String name = "Backlog Hydra";
    private static String imagePath = "monsters/backloghydra.png";
    private static int maxHp = 140;
    
    public BacklogHydra() {
        super(name, imagePath, maxHp);
        behaviors.add(new BacklogHydra_DeadlineDoom());
        behaviors.add(new BacklogHydra_QueueQuake());
        behaviors.add(new BacklogHydra_Multiply());
    }
}
