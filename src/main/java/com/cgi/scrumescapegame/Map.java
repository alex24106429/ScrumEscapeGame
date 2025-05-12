package com.cgi.scrumescapegame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {
    private List<Point> positions = new ArrayList<>();
    private int roomCount = 24;
    public void generateMapLayout() {
        //start room = x1 y1
        int x = 0;
        int y = 0;
        positions.add(new Point(x, y));
        Random rand = new Random();

        System.out.println("Kamer beginpositie: (" + x + ", " + y + ")");
        for (int i = 1; i <= roomCount; i++) {
            boolean valid = false;
            while (!valid) {

                int prevX = x;
                int prevY = y;

                int result = rand.nextInt(4);

                switch (result) {
                    case 0 -> x++;
                    case 1 -> y++;
                    case 2 -> x--;
                    case 3 -> y--;
                }

                if (checkAddRoom(x, y)) {
                    valid = true;
                    positions.add(new Point(x, y));
                } else {
                    x = prevX;
                    y = prevY;
                }
            }
        }
        for  ( int i = 1; i < positions.size(); i++) {
            Point p = positions.get(i);
            System.out.println("Kamer " + (i) + " positie: (" + p.x + ", " + p.y + ")");
        }
    }

    public boolean checkAddRoom(int x, int y) {
        if (x < -roomCount/2 || x > roomCount/2 || y < 1 || y > roomCount) {
            return false; // Kamer kan niet worden toegevoegd, omdat deze buiten de grenzen ligt
        }
        if (hasRoom(x, y)) {
            return false; // Kamer kan niet worden toegevoegd, omdat deze al bestaat
        }
//            if (check2x2(x, y)) {
//                return false; // Kamer kan niet worden toegevoegd, omdat deze al bestaat
//            }

        return true; // Kamer kan worden toegevoegd
    }
//    public boolean check2x2(int x, int y) {
//        return (hasRoom(x - 1, y) && hasRoom(x, y - 1) && hasRoom(x - 1, y - 1) ||
//                        hasRoom(x + 1, y) && hasRoom(x, y - 1) && hasRoom(x + 1, y - 1) ||
//                        hasRoom(x - 1, y) && hasRoom(x, y + 1) && hasRoom(x - 1, y + 1) ||
//                        hasRoom(x + 1, y) && hasRoom(x, y + 1) && hasRoom(x + 1, y + 1)
//        );
//    }

    public void generateMap() {
        // Find the min and max x and y values from the room positions
        int xMin = Integer.MAX_VALUE;
        int xMax = Integer.MIN_VALUE;
        int yMin = Integer.MAX_VALUE;
        int yMax = Integer.MIN_VALUE;

        // Loop through the positions to determine the grid boundaries
        for (Point p : positions) {
            xMin = Math.min(xMin, p.x);
            xMax = Math.max(xMax, p.x);
            yMin = Math.min(yMin, p.y);
            yMax = Math.max(yMax, p.y);
        }

        // Adjust the grid size to fit the max position
        int gridWidth = xMax - xMin + 1;
        int gridHeight = yMax - yMin + 1;

        // Print the grid
        for (int y = yMax; y >= yMin; y--) {  // Go from top (yMax) to bottom (yMin)
            for (int x = xMin; x <= xMax; x++) {
                if (hasRoom(x, y)) {
                    if (positions.get(positions.size() - 1).equals(new Point(x, y))) {
                        System.out.print("🔴");
                    } else {
                        System.out.print("⚪");
                    }
                } else {
                    System.out.print("🔳");
                }
            }
            System.out.println();
        }
    }
    public boolean hasRoom(int x, int y) {
        for (Point p : positions) {
            if (p.x == x && p.y == y) {
                return true; // Kamer bestaat al
            }
        }
        return false; // Kamer bestaat niet
    }
}
