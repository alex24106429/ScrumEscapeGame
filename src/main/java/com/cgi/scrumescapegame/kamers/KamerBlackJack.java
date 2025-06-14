package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Difficulty;
import com.cgi.scrumescapegame.Game;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Room;
import com.cgi.scrumescapegame.minigames.BlackjackMinigame;

public class KamerBlackJack extends Room {
    public KamerBlackJack(int roomX, int roomY) {
        super("Blackjack Kamer", "Blackjack en Scrum zijn raamwerken om risico's te beheren en met onzekerheid om te gaan, door gebruik te maken van korte, iteratieve cycli waarin je met onvolledige informatie de best mogelijke beslissing neemt, het resultaat inspecteert en de strategie daarop aanpast.", roomX, roomY);
    }
    @Override
    public void roomLogic(Player player, Difficulty difficulty) {
        setCleared(true);
        BlackjackMinigame minigame = new BlackjackMinigame();
        minigame.startMinigame(player, Game.scanner);
    }

    @Override
    public int getHue() {
        return 40;
    }
}
