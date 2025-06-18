package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Difficulty;
import com.cgi.scrumescapegame.Game;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.cgi.scrumescapegame.minigames.BlackjackMinigame;
import com.diogonunes.jcolor.Attribute;

public class KamerBlackJack extends Room {
    private static final BlackjackMinigame minigame = new BlackjackMinigame();
    public KamerBlackJack(int roomX, int roomY) {
        super("Blackjack Kamer", "Je stapt een kamer binnen waar de wetten van de kerker lijken te vervagen. Een enkele tafel staat in het midden, uitnodigend.\nDit is de Kamer van het Gecalculeerde Risico. De Architect geloofde dat het nemen van beslissingen met onvolledige informatie een cruciale vaardigheid is.\nBewijs hier je meesterschap in het inschatten van kansen.", roomX, roomY);
    }
    @Override
    public void roomLogic(Player player, Difficulty difficulty) {
        Game.tutorial.blackjackTutorial();
        if(getCleared()) {
            PrintMethods.typeTextColor("Wil je Blackjack spelen?", Attribute.BRIGHT_YELLOW_TEXT());
            PrintMethods.printColor("(j/n) > ", Attribute.BRIGHT_BLUE_TEXT());
            String input = Game.scanner.nextLine();
            if (input.toLowerCase().trim().startsWith("j")) {
                minigame.startMinigame(player, Game.scanner);
            }
        } else {
            minigame.startMinigame(player, Game.scanner);
        }
        setCleared(true);
    }

    @Override
    public int getHue() {
        return 40;
    }
}
