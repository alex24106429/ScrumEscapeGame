package com.cgi.scrumescapegame.enemies;

import com.cgi.scrumescapegame.enemyattacks.*;

public class MissAlignment extends Enemy{
    private static String name = "Miss Alignment";
    private static String imagepath = "monsters/healer.png";
    private static int maxHp = 400;

    public MissAlignment(){
        super(name, imagepath, maxHp);
        behaviors.add(new MissAlignment_DefinitionofHealed());
        behaviors.add(new MissAlignment_BacklogBombardment());
        behaviors.add(new MissAlignment_ForcedAlignment());
        behaviors.add(new MissAlignment_FinalScrum());
    }
}
