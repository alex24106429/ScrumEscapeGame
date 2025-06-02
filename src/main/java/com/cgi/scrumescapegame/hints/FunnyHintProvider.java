package com.cgi.scrumescapegame.hints;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cgi.scrumescapegame.Vraag;

public class FunnyHintProvider implements HintProvider {
    
    List<String> jokes = new ArrayList<>();

    public FunnyHintProvider() {
        jokes.add("Why did the Scrum Master bring a ladder to the Daily Scrum?\nTo help the team overcome high-level impediments.");
        jokes.add("How many Scrum Masters does it take to change a lightbulb?\nNone, but they'll facilitate a retrospective to find out why the lightbulb feels it isn't empowered to change itself.");
        jokes.add("Our sprint backlog is like a good wine.\nIt gets bigger with age, and everyone has strong opinions about it.");
        jokes.add("Sprint Planning is like a game of Tetris.\nYou try to fit all the stories in, but eventually, something just won't fit and everything comes crashing down on Friday.");
        jokes.add("What do you call a developer who documents everything perfectly in Scrum?\nA myth.");
        jokes.add("What's a Scrum Master's favorite exercise?\nRunning interference.");
        jokes.add("My Daily Scrum status update:\n\"Yesterday I did what I said I'd do. Today I'll do what I said I'd do. No impediments... other than this meeting.\"");
        jokes.add("Agile Transformation: Phase 1 is changing all meeting names.\nPhase 2 is wondering why nothing else changed.");
        jokes.add("What's the most common item added to the sprint backlog mid-sprint?\nPanic.");
        jokes.add("Why did the backlog get an award?\nFor outstanding performance in \"growing unpredictably.\"");
        jokes.add("What do you call a zombie who is good at Scrum?\nA Scrum-bie. He always delivers \"braaaaains\" value.");
        jokes.add("Definition of \"Done\" for this joke:\nYou smiled, or at least exhaled slightly faster through your nose.");
        jokes.add("Why are Scrum retrospectives like therapy?\nYou talk about your problems, but they're rarely fixed by the next session.");
        jokes.add("What's a developer's favorite part of Scrum?\nThe Sprint... to the coffee machine.");
        jokes.add("My product backlog is like a hydra.\nFor every item I remove something, two more appear!");
        jokes.add("Our \"Definition of Done\" is so long...\nIt has its own backlog.");
        jokes.add("What's a Scrum team's favorite board game?\nGuess Who? (the product owner is)");
        jokes.add("What's the difference between a Scrum Master and a project manager?\nOne helps you clear roadblocks, the other is the roadblock.");
        jokes.add("I tried to explain Scrum to my grandma.\nShe said, \"So it's like bingo, but with more meetings and less winning?\"");
        jokes.add("Definition of Done?\nMore like \"Definition of Hopefully We Won't Have to Touch This Again For At Least A Week.\"");
        jokes.add("Sprint Review: Where \"it works on my machine\" meets \"it definitely doesn't work on mine.\"");
        jokes.add("Why did the developer sit down during the Daily Scrum?\nBecause his \"update\" was going to be a long story.");
    }

    @Override
    public String getHint(Vraag vraag) {
        return this.jokes.get(new Random().nextInt(this.jokes.size()));
    }

    public String getHint() {
        return this.jokes.get(new Random().nextInt(this.jokes.size()));
    }
}