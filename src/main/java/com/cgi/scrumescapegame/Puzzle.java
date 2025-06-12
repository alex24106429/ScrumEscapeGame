package com.cgi.scrumescapegame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cgi.scrumescapegame.enemies.Enemy;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.cgi.scrumescapegame.hints.HintFactory;
import com.cgi.scrumescapegame.hints.HintProvider;
import com.cgi.scrumescapegame.observers.Deur;
import com.cgi.scrumescapegame.observers.PuzzleObserver;
import com.cgi.scrumescapegame.observers.PuzzleSubject;
import com.diogonunes.jcolor.Attribute;
import com.cgi.scrumescapegame.observers.FeedbackObserver;
import com.cgi.scrumescapegame.observers.ScoreBoard;

public class Puzzle implements PuzzleSubject {
    PuzzleObserver feedbackObserver = new FeedbackObserver();
    PuzzleObserver deur = new Deur();
    PuzzleObserver scoreBoard = new ScoreBoard();
    private final List<Vraag> vragen;
    private final List<PuzzleObserver> observers;
    private Vraag currentVraag;
    private Player player;

    public Puzzle() {
        this.vragen = new ArrayList<>();
        this.observers = new ArrayList<>();
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
        registerObserver(feedbackObserver);
        registerObserver(deur);
        registerObserver(scoreBoard);

        if (vragen.isEmpty()) {
            PrintMethods.printlnColor("Je hebt geluk! De puzzle bevat geen vragen.", Attribute.BRIGHT_GREEN_TEXT());
            player.getCurrentRoom().setCleared(true);
            return;
        }

        Scanner scanner = Game.scanner;

        for (int i = 0; i < vragen.size(); i++) {
            Vraag huidigeVraag = vragen.get(i);
            this.currentVraag = huidigeVraag;
            huidigeVraag.toonVraag();

            String gebruikersAntwoord = scanner.nextLine();

            boolean correct = huidigeVraag.controleerAntwoord(gebruikersAntwoord);
            if(Game.debug && gebruikersAntwoord.equals("skip")) correct = true;
            if (correct) {
                player.changeGold(10);
            } else {
                boolean shouldGiveHint;
                if(difficulty == Difficulty.HARD) {
                    shouldGiveHint = false;
                } else {
                    PrintMethods.printlnColor("Verkeerd antwoord! Wil je een hint? (j/n)", Attribute.BRIGHT_RED_TEXT());
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
                } else {
                    BattleSystem.startBattle(player, enemy, scanner);
                }
            }

            System.out.println("----------------------");
        }

        System.out.println("\nPuzzle voltooid!");
        notifyObserver(true);

    }
}

