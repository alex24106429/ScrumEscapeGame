package com.cgi.scrumescapegame.hints;

import com.cgi.scrumescapegame.Randomizer;

public class HintFactory {
    public static HintProvider getRandomHintProvider() {
        if (Randomizer.getRandomBoolean()) {
            return new HelpfulHintProvider();
        } else {
            return new FunnyHintProvider();
        }
    }
}