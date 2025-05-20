package com.cgi.scrumescapegame;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

import java.awt.Color;
import java.util.Map;

public class RoomRenderer {

    public static void renderRoom(Room room, Player player) {
        // Print titel met gradient
        TextToImageRenderer.printGradientText(room.getName(),
                new Color(255, 255, 255),
                new Color(127, 127, 127),
                new Color(63, 63, 63),
                new Color(31, 31, 31),
                2,
                true,
                false);

        // Print description
        PrintMethods.typeText(room.getDescription());

        // Print speler status
        PlayerPrinter.printStatus(player);

        // Print beschikbare aangrenzende kamers via helper
    }

    public static String renderAvailableRooms(Map<String, Boolean> adjacentRooms) {
        StringBuilder sb = new StringBuilder();

        if (adjacentRooms.getOrDefault("up", false)) sb.append("↑ ");
        if (adjacentRooms.getOrDefault("left", false)) sb.append("← ");
        if (adjacentRooms.getOrDefault("down", false)) sb.append("↓ ");
        if (adjacentRooms.getOrDefault("right", false)) sb.append("→ ");

        sb.append("( ");

        if (adjacentRooms.getOrDefault("up", false)) sb.append("W ");
        if (adjacentRooms.getOrDefault("left", false)) sb.append("A ");
        if (adjacentRooms.getOrDefault("down", false)) sb.append("S ");
        if (adjacentRooms.getOrDefault("right", false)) sb.append("D ");

        sb.append(")");

        return sb.toString();
    }
}
