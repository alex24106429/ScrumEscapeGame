package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Difficulty;
import com.cgi.scrumescapegame.Game;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Room;
import com.cgi.scrumescapegame.minigames.BlackjackMinigame;

public class KamerDailyStandup extends Room {
    public KamerDailyStandup(int roomX, int roomY) {
        super("Kamer daily standup", "Je bent in de daily standup kamer. Hier bespreken we wat we sinds de volgende standup gedaan hebben en hoe we verder gaan werken.", roomX, roomY);
    }

    @Override
    public boolean canUseKeyJoker() {
        return true;
    }
    
    @Override
    public void roomLogic(Player player, Difficulty difficulty) {
        setCleared(true);
        BlackjackMinigame minigame = new BlackjackMinigame();
        minigame.startMinigame(player, Game.scanner);
        // Specifieke acties voor de dailystandupkamer
    }

    @Override
    public int getHue() {
        return 45;
    }
}