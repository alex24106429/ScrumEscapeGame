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
        super("Blackjack Kamer", "Blackjack en Scrum zijn raamwerken om risico's te beheren en met onzekerheid om te gaan, door gebruik te maken van korte, iteratieve cycli waarin je met onvolledige informatie de best mogelijke beslissing neemt, het resultaat inspecteert en de strategie daarop aanpast.", roomX, roomY);
    }
    @Override
    public void roomLogic(Player player, Difficulty difficulty) {
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
