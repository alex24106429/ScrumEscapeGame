package com.cgi.scrumescapegame.puzzles;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cgi.scrumescapegame.BattleSystem;
import com.cgi.scrumescapegame.Difficulty;
import com.cgi.scrumescapegame.Game;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Randomizer;
import com.cgi.scrumescapegame.enemies.Enemy;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.cgi.scrumescapegame.hints.HintFactory;
import com.cgi.scrumescapegame.hints.HintProvider;
import com.cgi.scrumescapegame.observers.Deur;
import com.cgi.scrumescapegame.observers.PuzzleObserver;
import com.cgi.scrumescapegame.observers.PuzzleSubject;
import com.cgi.scrumescapegame.vragen.Vraag;
import com.diogonunes.jcolor.Attribute;

public class Puzzle implements PuzzleSubject {
    PuzzleObserver deur = new Deur();
    protected List<Vraag> vragen;
    private final List<PuzzleObserver> observers = new ArrayList<>();
    private Vraag currentVraag;
    private Player player;

    public Puzzle(List<Vraag> vragen) {
        this.vragen = vragen;
    }

    @Override
    public void registerObserver(PuzzleObserver observer){
        observers.add(observer);
    }

    @Override
    public void removeObserver(PuzzleObserver observer){
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(boolean isCorrect){
        for(PuzzleObserver observer : observers){
            observer.update(isCorrect, currentVraag, player);
        }
    }

    public void addQuestion(Vraag vraag) {
        this.vragen.add(vraag);
    }

    public void start(Player player, Enemy enemy, Difficulty difficulty) {
        this.player = player;
        registerObserver(deur);

        if (vragen.isEmpty()) {
            // Als je dit ziet is er iets heel erg misgegaan
            PrintMethods.printlnColor("Je hebt geluk! De puzzle bevat geen vragen.", Attribute.BRIGHT_GREEN_TEXT());
            player.getCurrentRoom().setCleared(true);
            return;
        }

        Scanner scanner = Game.scanner;

        Vraag huidigeVraag = vragen.get(Randomizer.getRandomInt(vragen.size()));
        this.currentVraag = huidigeVraag;
        huidigeVraag.toonVraag();

        String gebruikersAntwoord = scanner.nextLine();

        boolean correct = huidigeVraag.controleerAntwoord(gebruikersAntwoord);
        if(Game.debug && gebruikersAntwoord.equals("skip")) correct = true;
        if (correct) {
            player.gainExperience(50);
            player.changeGold(10);
        } else {
            boolean shouldGiveHint;
            if(difficulty == Difficulty.HARD) {
                shouldGiveHint = false;
            } else {
                PrintMethods.printlnColor("Verkeerd antwoord! Wil je een hint?", Attribute.BRIGHT_RED_TEXT());
                PrintMethods.printColor("(j/n) > ", Attribute.BRIGHT_BLUE_TEXT());
                String hintChoice = scanner.nextLine();
                shouldGiveHint = hintChoice.trim().toLowerCase().startsWith("j");
            }
            
            if (shouldGiveHint) {
                // Provide hint
                HintProvider hintProvider = HintFactory.getRandomHintProvider();
                PrintMethods.printlnColor(hintProvider.getHint(huidigeVraag), Attribute.CYAN_TEXT());
            }

            // Second chance
            PrintMethods.printlnColor("Probeer opnieuw: ", Attribute.BRIGHT_BLUE_TEXT());
            String secondAnswer = scanner.nextLine();
            boolean secondAnswerCorrect = huidigeVraag.controleerAntwoord(secondAnswer);
            if (Game.debug && secondAnswer.equals("skip")) secondAnswerCorrect = true;
            if (secondAnswerCorrect) {
                player.changeGold(5);
                player.gainExperience(25);
            } else {
                PrintMethods.typeTextColor("Het juiste antwoord was: " + huidigeVraag.getCorrectAntwoord(), Attribute.BRIGHT_YELLOW_TEXT());
                Game.pause(1000);
                BattleSystem.startBattle(player, enemy, scanner);
            }
        }

        notifyObserver(true);
    }
}

