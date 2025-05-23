package com.cgi.scrumescapegame.observers;

import com.cgi.scrumescapegame.Puzzle;

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
