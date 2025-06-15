package com.cgi.scrumescapegame.enemies;

import com.cgi.scrumescapegame.enemyattacks.MissAlignment_DefinitionofHealed;
import com.cgi.scrumescapegame.enemyattacks.SlimeMaster_SprintSnare;
import com.cgi.scrumescapegame.enemyattacks.SlimeMaster_UserSlurry;

public class MissAlignment extends Enemy{
    private static String name = "Miss Alignment";
    private static String imagepath = "monsters/healer.png";
    private static int maxHp = 100;

    public MissAlignment(){
        super(name, imagepath, maxHp);
        behaviors.add(new MissAlignment_DefinitionofHealed());
        behaviors.add(new SlimeMaster_SprintSnare());
        behaviors.add(new SlimeMaster_UserSlurry());
    }
}
