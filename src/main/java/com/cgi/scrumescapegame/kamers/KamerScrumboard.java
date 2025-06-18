package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Difficulty;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.enemies.ScopeCreeper;
import com.cgi.scrumescapegame.puzzles.PuzzleRooms;
import com.cgi.scrumescapegame.puzzles.ScrumboardPuzzle;

public class KamerScrumboard extends Room implements PuzzleRooms {
    public KamerScrumboard(int roomX, int roomY) {
        super("Scrumboard kamer", "De muren van dit vertrek zijn een levend mozaïek van bewegende tegels die de stroom van werk visualiseren.\nDit is de Galerij van Transparantie, waar elke taak zichtbaar is voor iedereen. Maar wees op je hoede.\nIn de kieren van het bord, waar de 'Definition of Done' vaag is, sluipt de Scope Creeper, altijd op zoek naar een kans om ongemerkt werk toe te voegen.", roomX, roomY);
        this.puzzle = new ScrumboardPuzzle();
    }

    @Override
    public void roomLogic(Player player, Difficulty difficulty) {
    }

    @Override
    public void startPuzzle(Player player, Difficulty difficulty) {
        puzzle.start(player, new ScopeCreeper(), difficulty);
    }

    @Override
    public int getHue() {
        return 240;
    }
}