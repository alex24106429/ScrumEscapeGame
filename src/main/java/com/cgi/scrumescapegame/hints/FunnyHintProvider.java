package com.cgi.scrumescapegame.hints;

import com.cgi.scrumescapegame.Vraag;

public class FunnyHintProvider implements HintProvider {
    @Override
    public String getHint(Vraag vraag) {
        return "Waarom stak de kip de weg over? Om bij de andere kant te komen!";
    }
}