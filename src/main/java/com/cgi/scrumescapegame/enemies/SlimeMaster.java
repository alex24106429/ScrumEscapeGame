package com.cgi.scrumescapegame.enemies;

import com.cgi.scrumescapegame.enemyattacks.SlimeMaster_CapitalSlime;
import com.cgi.scrumescapegame.enemyattacks.SlimeMaster_SprintSnare;
import com.cgi.scrumescapegame.enemyattacks.SlimeMaster_UserSlurry;

public class SlimeMaster extends Enemy{
    private static String name = "Slime Master";
    private static String imagepath = "monsters/slime";
    private static int maxHp = 90;

    public SlimeMaster(){
        super(name, imagepath, maxHp);
        behaviors.add(new SlimeMaster_CapitalSlime());
        behaviors.add(new SlimeMaster_SprintSnare());
        behaviors.add(new SlimeMaster_UserSlurry());
    }
}
