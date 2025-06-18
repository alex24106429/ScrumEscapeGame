package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.AssistantService;
import com.cgi.scrumescapegame.Difficulty;
import com.cgi.scrumescapegame.Player;

public class StartKamer extends Room {
    public StartKamer(int roomX, int roomY) {
        super("De Startkamer", "Je ontwaakt op koude, vochtige stenen. De lucht is zwaar en ruikt naar vergeten code en ozon.\nDit is de Drempel, de ingang van de Scrum Dungeon. De Architect heeft je hierheen gehaald als zijn laatste hoop.\nBewijs dat je de principes beheerst, of wordt een permanent onderdeel van deze kerker.", roomX, roomY);
    }

    @Override
    public void roomLogic(Player player, Difficulty difficulty) {
        AssistantService.playIntroduction();
        setCleared(true);
    }

    @Override
    public int getHue() {
        return 320;
    }
}