package com.cgi.scrumescapegame.enemies;

import com.cgi.scrumescapegame.enemyattacks.CodeGolem_StackOverflowStomp;
import com.cgi.scrumescapegame.enemyattacks.CodeGolem_StandupStun;

public class CodeGolem extends Enemy{
    private static String name = "Code Golem";
    private static String imagepath = "monsters/codegolem.png";
    private static int maxHp = 200;

    public CodeGolem(){
        super(name, imagepath, maxHp);
        behaviors.add(new CodeGolem_StackOverflowStomp());
        behaviors.add(new CodeGolem_StandupStun());
    }
}
