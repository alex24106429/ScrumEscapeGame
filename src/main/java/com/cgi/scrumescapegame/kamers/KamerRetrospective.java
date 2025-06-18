package com.cgi.scrumescapegame.kamers;

import com.cgi.scrumescapegame.Difficulty;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.enemies.SprintSnipper;
import com.cgi.scrumescapegame.puzzles.PuzzleRooms;
import com.cgi.scrumescapegame.puzzles.RetrospectivePuzzle;

public class KamerRetrospective extends Room implements PuzzleRooms {
    public KamerRetrospective(int roomX, int roomY) {
        super("Retrospective kamer", "Een serene, stille kamer. De muren zijn als spiegels die niet je uiterlijk, maar je acties en processen reflecteren.\nDit is het Heiligdom van de Reflectie. Kijk terug om vooruit te komen, en wees eerlijk zonder schuld te zoeken.\nWant waar vingerwijzen en kritiek de overhand nemen, scherpt de Sprint Snipper zijn scharen om je teamgeest aan stukken te knippen.", roomX, roomY);
        this.puzzle = new RetrospectivePuzzle();
    }

    @Override
    public void roomLogic(Player player, Difficulty difficulty) {
    }

    @Override
    public void startPuzzle(Player player, Difficulty difficulty) {
        puzzle.start(player, new SprintSnipper(), difficulty);
    }
    
    @Override
    public int getHue() {
        return 160;
    }
}