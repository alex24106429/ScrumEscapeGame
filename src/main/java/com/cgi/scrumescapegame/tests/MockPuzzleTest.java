package com.cgi.scrumescapegame.tests;

import com.cgi.scrumescapegame.Difficulty;
import com.cgi.scrumescapegame.Game;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Timer;
import com.cgi.scrumescapegame.enemies.CodeGolem;
import com.cgi.scrumescapegame.enemies.Enemy;
import com.cgi.scrumescapegame.kamers.StartKamer;
import com.cgi.scrumescapegame.puzzles.Puzzle;
import com.cgi.scrumescapegame.vragen.OpenVraag;
import com.cgi.scrumescapegame.vragen.Vraag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockPuzzleTest {

    public static void main(String[] args) {
        Game.timer = new Timer();
        Game.timer.setStartTime();


        // Setup dummy vraag
        Vraag vraag = new OpenVraag(
                "Hoeveel vingers steek ik op?",
                Arrays.asList("3"),
                "Meer dan 0 minder dan 5."
        );

        List<Vraag> vragenlijst = new ArrayList<>();
        vragenlijst.add(vraag);

        // Setup speler
        Player speler = new Player();
        speler.setCurrentRoom(new StartKamer(1, 1));
        speler.getCurrentRoom().setCleared(false);

        // Setup vijand
        Enemy enemy = new CodeGolem();

        // Setup puzzle
        Puzzle puzzle = new Puzzle(vragenlijst);

        // Maak mock observer en registreer die
        MockPuzzleObserver mockObserver = new MockPuzzleObserver();
        puzzle.registerObserver(mockObserver);

        // Start puzzle
        puzzle.start(speler, enemy, Difficulty.EASY);

        // Controle
        if (mockObserver.isUpdateAangeroepen && mockObserver.correctDoorgegeven) {
            System.out.println("✅ Mock observer werd succesvol aangeroepen met correct=true");
        } else {
            System.out.println("❌ Mock observer werd NIET aangeroepen zoals verwacht");
        }
    }
}

