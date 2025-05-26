package com.cgi.scrumescapegame.hints;

import com.cgi.scrumescapegame.Vraag;

public class HelpHintProvider implements HintProvider {
    @Override
    public String getHint(Vraag vraag) {
        return vraag.getHint();
    }
}