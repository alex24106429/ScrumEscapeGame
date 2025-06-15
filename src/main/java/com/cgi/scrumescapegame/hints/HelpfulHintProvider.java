package com.cgi.scrumescapegame.hints;

import com.cgi.scrumescapegame.vragen.Vraag;

public class HelpfulHintProvider implements HintProvider {
    @Override
    public String getHint(Vraag vraag) {
        return vraag.getHint();
    }
}