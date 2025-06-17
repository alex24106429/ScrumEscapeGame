package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Difficulty;
import com.cgi.scrumescapegame.Player;

public class EindKamer extends Room {
    public EindKamer(int roomX, int roomY) {
        super("Laatse Kamer", "Je duwt een massieve deur open en stapt het Sanctum van de Alignment binnen.\nDit is het hart van de kerker, de kern van de gevangenis van het Grote Impediment. De lucht knettert van corrupte energie.\nIn het midden van de kamer voel je een aanwezigheid die harmonie verdraait en focus vernietigt. Hier wacht Miss Alignment, de laatste bewaker.\nVersla haar, herstel de orde, en verdien je ontsnapping.", roomX, roomY);
    }

    @Override
    public void roomLogic(Player player, Difficulty difficulty) {
        setCleared(true);
    }

    @Override
    public int getHue() {
        return 0;
    }
}