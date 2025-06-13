package com.cgi.scrumescapegame.enemies;

import com.cgi.scrumescapegame.enemyattacks.ScopeCreeper_DefinitionErosion;
import com.cgi.scrumescapegame.enemyattacks.ScopeCreeper_FeatureSwarm;

public class ScopeCreeper extends Enemy {
    private static String name = "Scope Creeper";
    private static String imagePath = "monsters/scopecreeper.png";
    private static int maxHp = 50;
    
    public ScopeCreeper() {
        super(name, imagePath, maxHp);
        behaviors.add(new ScopeCreeper_DefinitionErosion());
        behaviors.add(new ScopeCreeper_FeatureSwarm());
    }
}
