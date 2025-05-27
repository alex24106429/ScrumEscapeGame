package com.cgi.scrumescapegame.hints;

import java.util.Random;

public class HintFactory {
    public static HintProvider getRandomHintProvider() {
        Random rand = new Random();
        if (rand.nextBoolean()) {
            return new HelpfulHintProvider();
        } else {
            return new FunnyHintProvider();
        }
    }
}