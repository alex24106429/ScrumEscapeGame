package com.cgi.scrumescapegame.vragen;

import com.cgi.scrumescapegame.Vraag;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.diogonunes.jcolor.Attribute;

public class OpenVraag implements Vraag {
    private String tekst;
    private String correctAntwoord;
    private String userinput;
    private String hint;

    public OpenVraag(String tekst, String correctAntwoord, String hint) {
        this.tekst = tekst;
        this.correctAntwoord = correctAntwoord;
        this.hint = hint;
    }

    @Override
    public String getTekst() {
        return tekst;
    }

    @Override
    public void toonVraag() {
        PrintMethods.printlnColor(tekst, new Attribute[]{Attribute.BRIGHT_YELLOW_TEXT(), Attribute.BOLD()});
        PrintMethods.printColor("> ", Attribute.BRIGHT_BLUE_TEXT());
    }

    @Override
    public boolean controleerAntwoord(String antwoord) {
        if (antwoord == null || antwoord.trim().isEmpty()) {
            return false;
        }
        String userinput = antwoord.trim().toLowerCase();
        if(userinput.equals(correctAntwoord.toLowerCase())){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String getCorrectAntwoord() {
        return correctAntwoord;
    }

    public String getUserinput(){
        return userinput;
    }

    @Override
    public String getHint() {
        return this.hint;
    }
}
