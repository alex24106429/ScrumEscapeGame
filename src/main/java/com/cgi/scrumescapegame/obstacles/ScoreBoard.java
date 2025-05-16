package com.cgi.scrumescapegame.obstacles;

import com.cgi.scrumescapegame.Observer;

public class ScoreBoard implements Observer {
    private int score;
    private int highScore;

    public ScoreBoard() {
        this.score = 0;
        this.highScore = 0;
    }

    @Override
    public void update(boolean isCorrect) {
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
