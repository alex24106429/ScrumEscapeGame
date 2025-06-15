package com.cgi.scrumescapegame.hints;

import com.cgi.scrumescapegame.vragen.Vraag;

public interface HintProvider {
    String getHint(Vraag vraag);
}