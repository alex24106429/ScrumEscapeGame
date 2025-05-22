package com.cgi.scrumescapegame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cgi.scrumescapegame.graphics.ImagePrinter;
import com.cgi.scrumescapegame.graphics.PrintMethods;

public class Map {
    private final List<Point> positions = new ArrayList<>();
    private final int roomCount = 24;
    public void generateMapLayout() {
        // Start room at (0,0)
        positions.add(new Point(0, 0));

        Random rand = new Random();

        if(Game.debug) System.out.println("Kamer beginpositie: (0, 0)");

        // We need to add 'roomCount' more rooms.
        // The list starts with 1 room, so we need to reach a size of roomCount + 1.
        int targetRoomCount = roomCount + 1;

        // Keep adding rooms until we have the desired total
        while (positions.size() < targetRoomCount) {
            boolean roomAddedThisAttempt = false;
            // This inner loop keeps trying random existing rooms and directions
            // until a valid position for a *new* room is found.
            while (!roomAddedThisAttempt) {
                // 1. Pick a random existing room from the list
                int randomIndex = rand.nextInt(positions.size());
                Point baseRoom = positions.get(randomIndex);

                // 2. Try a random direction (up, down, left, or right) from the chosen room
                int newX = baseRoom.x;
                int newY = baseRoom.y;
                int direction = rand.nextInt(4); // 0: Right, 1: Up, 2: Left, 3: Down

                switch (direction) {
                    case 0 -> newX++;
                    case 1 -> newY++;
                    case 2 -> newX--;
                    case 3 -> newY--;
                }

                // 3. Check if the potential new position is valid
                if (checkAddRoom(newX, newY)) {
                    // 4. If valid, add it to the list and mark success for this room-adding iteration
                    positions.add(new Point(newX, newY));
                    roomAddedThisAttempt = true; // Exit the inner while loop
                    // System.out.println("Added room at (" + newX + ", " + newY + ") adjacent to (" + baseRoom.x + ", " + baseRoom.y + ")"); // Optional debug print
                }
                // If checkAddRoom returned false, the inner loop repeats, picking another random existing room and direction.
            }
            // Once a room is successfully added, the outer while loop continues if the target count hasn't been reached.
        }

        // Print the positions of all rooms (excluding the start room if matching original output style)
        // The original code printed rooms 1 to roomCount, which are indices 1 to roomCount in the list.
         for  ( int i = 1; i < positions.size(); i++) {
            Point p = positions.get(i);
            if(Game.debug) System.out.println("Kamer " + (i) + " positie: (" + p.x + ", + " + p.y + ")");
        }
    }

    public boolean checkAddRoom(int x, int y) {
        if (x < -roomCount/2 || x > roomCount/2 || y < 1 || y > roomCount) {
            return false; // Kamer kan niet worden toegevoegd, omdat deze buiten de grenzen ligt
        }
        return !hasRoom(x, y);
    }

    public void printMap(Player player) {
        int playerX = player.currentRoom.getCurrentPosition().x;
        int playerY = player.currentRoom.getCurrentPosition().y;

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

        // Calculate logical image dimensions (as if rooms were 1x1)
        // +1 because if xMin=0, xMax=0, width is 1 pixel.
        int logicalWidth = xMax - xMin + 1;
        int logicalHeight = yMax - yMin + 1;

        // Ensure dimensions are positive
        if (logicalWidth <= 0 || logicalHeight <= 0) return;

        // Actual image dimensions for 2x2 rooms
        int imageWidth = logicalWidth * 2;
        int imageHeight = logicalHeight * 2;

        BufferedImage mapImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

        // Define colors
        Color playerColor = Color.BLUE;
        Color lastRoomColor = Color.RED;
        Color roomColor = Color.BLACK;
        Color emptySpaceColor = Color.DARK_GRAY;

        // Populate the image
        // Note: BufferedImage y-coordinates are 0 at the top, increasing downwards.
        // We need to map world coordinates (x, y) to image pixel coordinates (imgX, imgY).
        for (int worldY = yMax; worldY >= yMin; worldY--) {
            for (int worldX = xMin; worldX <= xMax; worldX++) {
                // Convert world coordinates to logical 1x1 image pixel coordinates
                int logicalImgX = worldX - xMin;
                int logicalImgY = yMax - worldY; // Invert Y-axis: world yMax is img y=0

                // Scale to get top-left pixel of the 2x2 block in the actual image
                int imgX = logicalImgX * 2;
                int imgY = logicalImgY * 2;

                Color pixelColor;

                if (worldX == playerX && worldY == playerY) {
                    pixelColor = playerColor;
                } else if (hasRoom(worldX, worldY)) {
                    // Ensure positions is not empty before calling getLast()
                    if (!positions.isEmpty() && positions.getLast().equals(new Point(worldX, worldY))) {
                        pixelColor = lastRoomColor;
                    } else {
                        pixelColor = roomColor;
                    }
                } else {
                    pixelColor = emptySpaceColor;
                }
                
                // Set the 2x2 block of pixels
                mapImage.setRGB(imgX,     imgY,     pixelColor.getRGB());
                mapImage.setRGB(imgX + 1, imgY,     pixelColor.getRGB());
                mapImage.setRGB(imgX,     imgY + 1, pixelColor.getRGB());
                mapImage.setRGB(imgX + 1, imgY + 1, pixelColor.getRGB());
            }
        }
        ImagePrinter.printBufferedImage(mapImage);
    }

    public boolean hasRoom(int x, int y) {
        for (Point p : positions) {
            if (p.x == x && p.y == y) {
                return true; // Kamer bestaat al
            }
        }
        return false; // Kamer bestaat niet
    }

    public List<Point> getPositions() {
        return positions;
    }

    public java.util.Map<String, Boolean> getAdjacentRoomStatus(int x, int y) {
        java.util.Map<String, Boolean> adjacentStatus = new java.util.HashMap<>();

        if(Game.debug) System.out.println("Positions list size: " + positions.size());
        if(Game.debug) System.out.println("Positions list contents: " + positions);

        // Default all directions to false before checking
        adjacentStatus.put("right", false);
        adjacentStatus.put("left", false);
        adjacentStatus.put("up", false);
        adjacentStatus.put("down", false);

        for (Point p : positions) {
            if (p.x == x + 1 && p.y == y) {
                adjacentStatus.put("right", hasRoom(x + 1, y));
            }
            if (p.x == x - 1 && p.y == y) {
                adjacentStatus.put("left", hasRoom(x - 1, y));
            }
            if (p.x == x && p.y == y + 1) {
                adjacentStatus.put("up", hasRoom(x, y + 1));
            }
            if (p.x == x && p.y == y - 1) {
                adjacentStatus.put("down", hasRoom(x, y - 1));
            }
        }

        return adjacentStatus;
    }



}
