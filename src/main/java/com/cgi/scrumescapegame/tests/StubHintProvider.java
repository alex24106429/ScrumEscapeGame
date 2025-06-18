package com.cgi.scrumescapegame.tests;

import com.cgi.scrumescapegame.hints.HintProvider;
import com.cgi.scrumescapegame.vragen.Vraag;

public class StubHintProvider implements HintProvider {
    @Override
    public String getHint(Vraag vraag) {
        return "Stub hint: Test hint altijd hetzelfde";
    }
}
