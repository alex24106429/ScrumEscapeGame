package com.cgi.scrumescapegame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cgi.scrumescapegame.graphics.ImagePrinter;
import com.cgi.scrumescapegame.kamers.*; // Import all room types

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

        int roomExtentXMin = Integer.MAX_VALUE;
        int roomExtentXMax = Integer.MIN_VALUE;
        int roomExtentYMin = Integer.MAX_VALUE;
        int roomExtentYMax = Integer.MIN_VALUE;

        for (Point p : positions) {
            roomExtentXMin = Math.min(roomExtentXMin, p.x);
            roomExtentXMax = Math.max(roomExtentXMax, p.x);
            roomExtentYMin = Math.min(roomExtentYMin, p.y);
            roomExtentYMax = Math.max(roomExtentYMax, p.y);
        }

        int drawAreaXMin = roomExtentXMin - 1;
        int drawAreaXMax = roomExtentXMax + 1;
        int drawAreaYMin = roomExtentYMin - 1;
        int drawAreaYMax = roomExtentYMax + 1;

        int logicalMapWidth = drawAreaXMax - drawAreaXMin + 1;
        int logicalMapHeight = drawAreaYMax - drawAreaYMin + 1;

        if (logicalMapWidth <= 0 || logicalMapHeight <= 0) {
            System.out.println("Invalid map dimensions calculated. Cannot print map.");
            return;
        }

        int pixelImageWidth = logicalMapWidth * 2;
        int pixelImageHeight = logicalMapHeight * 2;

        BufferedImage mapImage = new BufferedImage(pixelImageWidth, pixelImageHeight, BufferedImage.TYPE_INT_RGB);

        Color playerColor = Color.BLUE;
        Color emptySpaceColor = Color.DARK_GRAY;
        Color outlineColor = Color.LIGHT_GRAY; // Color for the 1-pixel outline

        for (int worldY = drawAreaYMax; worldY >= drawAreaYMin; worldY--) {
            for (int worldX = drawAreaXMin; worldX <= drawAreaXMax; worldX++) {
                int logicalImgX = worldX - drawAreaXMin;
                int logicalImgY = drawAreaYMax - worldY;

                int imgX = logicalImgX * 2; // Top-left X of the 2x2 block
                int imgY = logicalImgY * 2; // Top-left Y of the 2x2 block

                if (worldX == playerX && worldY == playerY) {
                    // Player tile: fill 2x2 block with playerColor
                    mapImage.setRGB(imgX,     imgY,     playerColor.getRGB());
                    mapImage.setRGB(imgX + 1, imgY,     playerColor.getRGB());
                    mapImage.setRGB(imgX,     imgY + 1, playerColor.getRGB());
                    mapImage.setRGB(imgX + 1, imgY + 1, playerColor.getRGB());
                } else if (hasRoom(worldX, worldY)) {
                    // Room tile: determine color based on room type
                    Room currentRoom = getRoomAt(worldX, worldY, Game.rooms);
                    Color roomColor = Color.BLACK; // Default color

                    if (currentRoom != null) {
                        float hue = 0.0f;
                        if (currentRoom instanceof StartKamer) {
                            hue = 45f / 360f;
                        } else if (currentRoom instanceof KamerScrumboard) {
                            hue = 140f / 360f;
                        } else if (currentRoom instanceof KamerReview) {
                            hue = 240f / 360f;
                        } else if (currentRoom instanceof KamerRetrospective) {
                            hue = 275f / 360f;
                        } else if (currentRoom instanceof KamerPlanning) {
                            hue = 60f / 360f;
                        } else if (currentRoom instanceof KamerDailyStandup) {
                            hue = 35f / 360f;
                        } else if (currentRoom instanceof EindKamer) {
                            hue = 0f / 360f;
                        }
                        roomColor = Color.getHSBColor(hue, 0.5f, 0.2f);
                    }

                    mapImage.setRGB(imgX,     imgY,     roomColor.getRGB());
                    mapImage.setRGB(imgX + 1, imgY,     roomColor.getRGB());
                    mapImage.setRGB(imgX,     imgY + 1, roomColor.getRGB());
                    mapImage.setRGB(imgX + 1, imgY + 1, roomColor.getRGB());
                } else {
                    // Empty space tile: determine pixel colors for outline
                    Color[] pixelBlockColors = new Color[4]; // P00, P10, P01, P11
                                                                // (imgX,imgY), (imgX+1,imgY), (imgX,imgY+1), (imgX+1,imgY+1)
                    
                    // Initialize all 4 pixels to emptySpaceColor
                    pixelBlockColors[0] = emptySpaceColor; // Top-left
                    pixelBlockColors[1] = emptySpaceColor; // Top-right
                    pixelBlockColors[2] = emptySpaceColor; // Bottom-left
                    pixelBlockColors[3] = emptySpaceColor; // Bottom-right

                    // Check North neighbor (worldY + 1)
                    if (hasRoom(worldX, worldY + 1)) {
                        pixelBlockColors[0] = outlineColor; // Top-left pixel of current empty tile
                        pixelBlockColors[1] = outlineColor; // Top-right pixel of current empty tile
                    }
                    // Check South neighbor (worldY - 1)
                    if (hasRoom(worldX, worldY - 1)) {
                        pixelBlockColors[2] = outlineColor; // Bottom-left pixel
                        pixelBlockColors[3] = outlineColor; // Bottom-right pixel
                    }
                    // Check East neighbor (worldX + 1)
                    if (hasRoom(worldX + 1, worldY)) {
                        pixelBlockColors[1] = outlineColor; // Top-right pixel
                        pixelBlockColors[3] = outlineColor; // Bottom-right pixel
                    }
                    // Check West neighbor (worldX - 1)
                    if (hasRoom(worldX - 1, worldY)) {
                        pixelBlockColors[0] = outlineColor; // Top-left pixel
                        pixelBlockColors[2] = outlineColor; // Bottom-left pixel
                    }
                    
                    mapImage.setRGB(imgX,     imgY,     pixelBlockColors[0].getRGB());
                    mapImage.setRGB(imgX + 1, imgY,     pixelBlockColors[1].getRGB());
                    mapImage.setRGB(imgX,     imgY + 1, pixelBlockColors[2].getRGB());
                    mapImage.setRGB(imgX + 1, imgY + 1, pixelBlockColors[3].getRGB());
                }
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

    private Room getRoomAt(int x, int y, List<Room> rooms) {
        for (Room room : rooms) {
            if (room.roomX == x && room.roomY == y) {
                return room;
            }
        }
        return null;
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
