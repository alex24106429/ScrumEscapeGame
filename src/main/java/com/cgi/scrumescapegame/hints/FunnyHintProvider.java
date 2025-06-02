package com.cgi.scrumescapegame.hints;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cgi.scrumescapegame.Vraag;

public class FunnyHintProvider implements HintProvider {
    
    List<String> jokes = new ArrayList<>();

    public FunnyHintProvider() {
        jokes.add("Waarom nam de Scrum Master een ladder mee naar de Daily?\nOm het team te helpen over hoge obstakels heen te komen.");
        jokes.add("Hoeveel Scrum Masters heb je nodig om een lamp te vervangen?\nGeen één. Ze faciliteren gewoon een retro om te bespreken waarom de lamp zichzelf niet empowered voelt.");
        jokes.add("Onze sprint backlog is als goede wijn:\nHij wordt groter met de tijd en iedereen heeft er een mening over.");
        jokes.add("Sprint Planning is net Tetris:\nJe probeert alles passend te maken, maar vrijdag dondert alles alsnog in elkaar.");
        jokes.add("Wat noem je een developer die alles perfect documenteert?\nEen legende.");
        jokes.add("Wat is de favoriete sport van een Scrum Master?\nImpediment-hordenlopen.");
        jokes.add("Mijn Daily Scrum update:\n\"Gisteren heb ik gedaan wat ik zei. Vandaag doe ik wat ik zei. Geen obstakels... behalve deze meeting.\"");
        jokes.add("Agile transformatie in twee stappen:\nStap 1: Hernoem alle meetings. Stap 2: Vraag je af waarom er verder niks verandert.");
        jokes.add("Wat wordt het vaakst toegevoegd aan de sprint backlog tijdens een sprint?\nPaniekaanvallen.");
        jokes.add("Waarom kreeg de backlog een award?\nVoor uitmuntende prestaties in ongecontroleerd groeien.");
        jokes.add("Wat noem je een zombie die goed is in Scrum?\nEen Scrombie. Altijd waarde leveren: \"braaaaiiins\".");
        jokes.add("Definitie van ‘Klaar’ voor deze grap:\nJe glimlachte, of blies net iets harder uit je neus.");
        jokes.add("Waarom zijn retrospectives net therapie?\nJe praat over je problemen, maar de volgende week zijn ze er nog steeds.");
        jokes.add("Wat is het favoriete onderdeel van Scrum voor developers?\nDe Sprint... naar de koffiemachine.");
        jokes.add("Mijn product backlog lijkt op een hydra:\nVoor elk item dat ik verwijder, komen er twee terug.");
        jokes.add("Onze Definition of Done is zo lang...\nHij heeft een eigen backlog.");
        jokes.add("Wat is het favoriete bordspel van een Scrum-team?\nWie is het? (De Product Owner, meestal onzichtbaar).");
        jokes.add("Wat is het verschil tussen een Scrum Master en een projectmanager?\nDe één helpt obstakels wegnemen, de ander is het obstakel.");
        jokes.add("Ik probeerde Scrum uit te leggen aan mijn oma.\nZe zei: \"Dus het is bingo met meer meetings en minder prijzen?\"");
        jokes.add("Definition of Done?\nMeer zoals: 'Hopelijk hoeven we dit voorlopig niet meer aan te raken.'");
        jokes.add("Sprint Review: waar 'het werkt op mijn laptop' botst met 'het crasht op de jouwe'.");
        jokes.add("Waarom ging de developer tijdens de Daily Scrum zitten?\nZijn update was een lang verhaal.");

    }

    @Override
    public String getHint(Vraag vraag) {
        return this.jokes.get(new Random().nextInt(this.jokes.size()));
    }

    public String getHint() {
        return this.jokes.get(new Random().nextInt(this.jokes.size()));
    }
}