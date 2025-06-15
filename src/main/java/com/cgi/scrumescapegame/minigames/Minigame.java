package com.cgi.scrumescapegame.minigames;

import java.util.Scanner;

import com.cgi.scrumescapegame.Player;

public interface Minigame {
    String getName();
    String getDescription();
    void startMinigame(Player player, Scanner scanner);
    boolean isSuccessful();
}