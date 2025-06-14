package com.cgi.scrumescapegame.observers;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.Vraag;

public class FeedbackObserver implements PuzzleObserver {
    // Is dit wel nodig?
    // isCorrect is altijd true
    @Override
    public void update(boolean isCorrect, Vraag vraag, Player player) {
        // if (isCorrect) {
        // } else {
        //     System.out.println("Helaas, dat is niet correct.");
        //     System.out.println("Het juiste antwoord is: " + vraag.getCorrectAntwoord());
        // }
    }
}