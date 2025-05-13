package com.cgi.scrumescapegame.vragen;

import com.cgi.scrumescapegame.Vraag;

public class OpenVraag implements Vraag {
    private String tekst;
    private String correctAntwoord;
    private String userinput;

    public OpenVraag(String tekst, String correctAntwoord) {
        this.tekst = tekst;
        this.correctAntwoord = correctAntwoord;
    }

    @Override
    public String getTekst() {
        return tekst;
    }

    @Override
    public void toonVraag() {
        System.out.println("Openvraag: " + tekst);
        System.out.println("Uw antwoord: ");
    }

    @Override
    public boolean controleerAntwoord(String antwoord) {
        if (antwoord == null || antwoord.trim().isEmpty()) {
            return false;
        }
        String userinput = antwoord.trim().toLowerCase();
        if(userinput.equals(correctAntwoord)){
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
}
