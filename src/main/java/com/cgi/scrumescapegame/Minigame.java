package com.cgi.scrumescapegame;

import java.util.Scanner;

public interface Minigame {
    String getName();
    String getDescription();
    void startMinigame(Player player, Scanner scanner);
    boolean isSuccessful();
}