package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Difficulty;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.enemies.CodeGolem;
import com.cgi.scrumescapegame.puzzles.PlanningPuzzle;
import com.cgi.scrumescapegame.puzzles.PuzzleRooms;

public class KamerPlanning extends Room implements PuzzleRooms {
    public KamerPlanning(int roomX, int roomY) {
        super("Kamer Planning", "Je betreedt een immense zaal vol onafgemaakte standbeelden en lege blauwdrukken.\nDit is de Werkplaats van de Focus. Hier wordt chaos omgezet in een plan, en een vaag idee in een concreet Sprint Doel.\nBreng helderheid in de duisternis, anders zal de ongevormde materie van het werk zelf tot leven komen als een onstuitbare Code Golem.", roomX, roomY);
        this.puzzle = new PlanningPuzzle();
    }

    @Override
    public void roomLogic(Player player, Difficulty difficulty) {
    }

    @Override
    public void startPuzzle(Player player, Difficulty difficulty) {
        puzzle.start(player, new CodeGolem(), difficulty);
    }

    @Override
    public int getHue() {
        return 120;
    }
}