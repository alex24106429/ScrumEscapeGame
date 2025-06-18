package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.BattleSystem;
import com.cgi.scrumescapegame.Difficulty;
import com.cgi.scrumescapegame.Game;
import com.cgi.scrumescapegame.GamePrints;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.enemies.MissAlignment;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.diogonunes.jcolor.Attribute;

public class EindKamer extends Room {
    public EindKamer(int roomX, int roomY) {
        super("Laatse Kamer", "Je duwt een massieve deur open en stapt het Sanctum van de Alignment binnen.\nDit is het hart van de kerker, de kern van de gevangenis van het Grote Impediment. De lucht knettert van corrupte energie.\nIn het midden van de kamer voel je een aanwezigheid die harmonie verdraait en focus vernietigt. Hier wacht Miss Alignment, de laatste bewaker.\nVersla haar, herstel de orde, en verdien je ontsnapping.", roomX, roomY);
    }

    @Override
    public void roomLogic(Player player, Difficulty difficulty) {
        setCleared(true);
        if(player.getLevel() < 5) {
            PrintMethods.typeTextColor("Je moet minstens LVL 5 zijn om het eindgevecht te starten, kom later terug.", Attribute.BRIGHT_RED_TEXT());
            return;
        }
        PrintMethods.printlnColor("Weet je zeker dat je voorbereid bent om Miss Alignment te verslaan?", Attribute.BRIGHT_YELLOW_TEXT(), Attribute.BOLD());
        PrintMethods.printColor("(j/n) > ", Attribute.BRIGHT_BLUE_TEXT());

        String input = Game.scanner.nextLine();
        if(input.trim().toLowerCase().startsWith("j")) {
            BattleSystem.startBattle(player, new MissAlignment(), Game.scanner);
        
            GamePrints.printGameEnd(player);
        }
    }

    @Override
    public int getHue() {
        return 0;
    }
}