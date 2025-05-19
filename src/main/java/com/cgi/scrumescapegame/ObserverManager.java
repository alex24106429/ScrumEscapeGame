package com.cgi.scrumescapegame;

import com.cgi.scrumescapegame.Puzzle;
import com.cgi.scrumescapegame.obstacles.Deur;
import com.cgi.scrumescapegame.MonsterObserver;
import com.cgi.scrumescapegame.obstacles.ScoreBoard;

public class ObserverManager {
    private ScoreBoard scoreBoard;
    public void startAllObservers(Puzzle puzzle){
        Deur deur = new Deur();
        ScoreBoard scoreBoard = new ScoreBoard();
        puzzle.registerObserver(deur);
        puzzle.registerObserver(new MonsterObserver());
        puzzle.registerObserver(scoreBoard);
        this.scoreBoard = scoreBoard;
    }

    public ScoreBoard getScoreBoard(){
        return scoreBoard;
    }

    public void pingObservers(Puzzle puzzle, boolean isCorrect){
        puzzle.notifyObserver(isCorrect);
    }
}
