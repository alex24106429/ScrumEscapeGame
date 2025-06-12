package com.cgi.scrumescapegame.observers;

import com.cgi.scrumescapegame.Vraag;
import com.cgi.scrumescapegame.Player;

public class ScoreBoard implements PuzzleObserver {
    private int score;
    private int highScore;

    public ScoreBoard() {
        this.score = 0;
        this.highScore = 0;
    }
    public void update(boolean isCorrect, Vraag vraag, Player player) {
        if (isCorrect) {
            score += 10;
            System.out.println("Correct! Score: " + score);
        }
        if (score > highScore) {
            highScore = score;
        }
    }

    public int getScore() {
        return score;
    }

    public int getHighScore() {
        return highScore;
    }
}
