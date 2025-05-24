package com.cgi.scrumescapegame;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import java.util.Map;

import com.cgi.scrumescapegame.graphics.ImagePrinter;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.cgi.scrumescapegame.items.Torch;
import com.cgi.scrumescapegame.kamers.EindKamer;
import com.cgi.scrumescapegame.kamers.KamerDailyStandup;
import com.cgi.scrumescapegame.kamers.KamerPlanning;
import com.cgi.scrumescapegame.kamers.KamerRetrospective;
import com.cgi.scrumescapegame.kamers.KamerReview;
import com.cgi.scrumescapegame.kamers.KamerScrumboard;
import com.cgi.scrumescapegame.kamers.StartKamer;
import com.diogonunes.jcolor.Attribute;

public class MapPrinter {
	public static void printMap(Player player) {
        int playerX = player.currentRoom.getCurrentPosition().x;
        int playerY = player.currentRoom.getCurrentPosition().y;

        int roomExtentXMin = Integer.MAX_VALUE;
        int roomExtentXMax = Integer.MIN_VALUE;
        int roomExtentYMin = Integer.MAX_VALUE;
        int roomExtentYMax = Integer.MIN_VALUE;

        for (Point p : GameMap.positions) {
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

        int playerColor = Color.HSBtoRGB(180f / 360f, 1.0f, 1.0f);
        int emptySpaceColor = Color.HSBtoRGB(0.0f, 0.0f, 0.2f);
        int outlineColor = Color.HSBtoRGB(0.0f, 0.0f, 0.7f);

        // Calculate player's top-left pixel coordinates in the mapImage space
        int playerPxMin = (playerX - drawAreaXMin) * 2;
        int playerPyMin = (drawAreaYMax - playerY) * 2; // Y is inverted for image coordinates

        // Define the player's 2x2 tile area in continuous pixel coordinates.
        double playerRectPx1 = (double)playerPxMin;
        double playerRectPy1 = (double)playerPyMin;
        double playerRectPx2 = (double)playerPxMin + 2.0;
        double playerRectPy2 = (double)playerPyMin + 2.0;

        for (int worldY = drawAreaYMax; worldY >= drawAreaYMin; worldY--) {
            for (int worldX = drawAreaXMin; worldX <= drawAreaXMax; worldX++) {
                int logicalImgX = worldX - drawAreaXMin;
                int logicalImgY = drawAreaYMax - worldY;

                int imgX = logicalImgX * 2; // Top-left X of the 2x2 pixel block for this world tile
                int imgY = logicalImgY * 2; // Top-left Y of the 2x2 pixel block for this world tile

                if (worldX == playerX && worldY == playerY) {
                    // Player tile: fill 2x2 block with playerColor
                    mapImage.setRGB(imgX,     imgY,     playerColor);
                    mapImage.setRGB(imgX + 1, imgY,     playerColor);
                    mapImage.setRGB(imgX,     imgY + 1, playerColor);
                    mapImage.setRGB(imgX + 1, imgY + 1, playerColor);
                } else if (GameMap.hasRoom(worldX, worldY)) {
                    Room currentRoom = GameMap.getRoomAt(worldX, worldY, Game.rooms);
                    
                    if (currentRoom != null) {
                        float hue = getRoomHue(currentRoom);
 
                        // Apply pixel-based brightness for visible rooms
                        for (int py_in_tile = 0; py_in_tile < 2; py_in_tile++) {
                            for (int px_in_tile = 0; px_in_tile < 2; px_in_tile++) {
                                int currentPixelXGlobal = imgX + px_in_tile;
                                int currentPixelYGlobal = imgY + py_in_tile;

                                // Center of the current pixel being rendered
                                double currentPixelCenterX = currentPixelXGlobal + 0.5;
                                double currentPixelCenterY = currentPixelYGlobal + 0.5;

                                // Calculate the shortest Euclidean distance from the center of the current pixel
                                // to any point within the player's 2x2 pixel area.
                                double closestXInPlayerRect = Math.max(playerRectPx1, Math.min(currentPixelCenterX, playerRectPx2));
                                double closestYInPlayerRect = Math.max(playerRectPy1, Math.min(currentPixelCenterY, playerRectPy2));

                                double dx = currentPixelCenterX - closestXInPlayerRect;
                                double dy = currentPixelCenterY - closestYInPlayerRect;
                                
                                double pixelEuclideanDistance = Math.sqrt(dx * dx + dy * dy);

                                float minDistance = 1.0f;
                                float maxDistance = 4.0f;
                                float fullBrightness = 0.5f;
                                float noBrightness = 0.0f;

                                // If the player has a Torch, make the lighting brighter
                                if (player.getItems().stream().anyMatch(item -> item instanceof Torch)) {
                                    fullBrightness = 0.8f;
                                    maxDistance = 8.0f;
                                }

                                float brightness = (float) Math.max(noBrightness, fullBrightness * (float)Math.pow(Math.max(0.0f, 1.0f - Math.max(0.0f, (pixelEuclideanDistance - minDistance) / (maxDistance - minDistance))), 2.0f));

                                Color pixelColor = Color.getHSBColor(hue, 0.5f, brightness);
                                mapImage.setRGB(currentPixelXGlobal, currentPixelYGlobal, pixelColor.getRGB());
                            }
                        }
                    }
                } else {
                    int[] pixelBlockColors = new int[4];
                    
                    pixelBlockColors[0] = emptySpaceColor;
                    pixelBlockColors[1] = emptySpaceColor;
                    pixelBlockColors[2] = emptySpaceColor;
                    pixelBlockColors[3] = emptySpaceColor;

                    if (GameMap.hasRoom(worldX, worldY + 1)) {
                        pixelBlockColors[0] = outlineColor;
                        pixelBlockColors[1] = outlineColor;
                    }
                    if (GameMap.hasRoom(worldX, worldY - 1)) {
                        pixelBlockColors[2] = outlineColor;
                        pixelBlockColors[3] = outlineColor;
                    }
                    if (GameMap.hasRoom(worldX + 1, worldY)) {
                        pixelBlockColors[1] = outlineColor;
                        pixelBlockColors[3] = outlineColor;
                    }
                    if (GameMap.hasRoom(worldX - 1, worldY)) {
                        pixelBlockColors[0] = outlineColor;
                        pixelBlockColors[2] = outlineColor;
                    }
                    
                    mapImage.setRGB(imgX,     imgY,     pixelBlockColors[0]);
                    mapImage.setRGB(imgX + 1, imgY,     pixelBlockColors[1]);
                    mapImage.setRGB(imgX,     imgY + 1, pixelBlockColors[2]);
                    mapImage.setRGB(imgX + 1, imgY + 1, pixelBlockColors[3]);
                }
            }
        }
        ImagePrinter.printBufferedImage(mapImage);
        printAvailableRooms(player);
    }

    private static void printAvailableRooms(Player player) {
        int playerX = player.currentRoom.getCurrentPosition().x;
        int playerY = player.currentRoom.getCurrentPosition().y;

        PrintMethods.printColor("Available Rooms: ", Attribute.BOLD());
        Map<String, Point> directions = new LinkedHashMap<>();
        directions.put("up", new Point(playerX, playerY + 1));
        directions.put("left", new Point(playerX - 1, playerY));
        directions.put("down", new Point(playerX, playerY - 1));
        directions.put("right", new Point(playerX + 1, playerY));

        for (Map.Entry<String, Boolean> entry : player.currentRoom.adjacentRooms.entrySet()) {
            String internalDirectionKey = entry.getKey(); // This will be "up", "down", "left", "right"
            boolean isAvailable = entry.getValue();

            if (isAvailable) {
                Point targetPos = directions.get(internalDirectionKey);
                if (targetPos != null) {
                    Room adjacentRoom = GameMap.getRoomAt(targetPos.x, targetPos.y, Game.rooms);
                    if (adjacentRoom != null) {
                        float hue = getRoomHue(adjacentRoom);
                        Color roomColor = Color.getHSBColor(hue, 1.0f, 0.5f);
                        Attribute textColor = Attribute.TEXT_COLOR(roomColor.getRed(), roomColor.getGreen(), roomColor.getBlue());

                        String displayDirection;
                        if (internalDirectionKey.equals("up")) {
                            displayDirection = "forward";
                        } else if (internalDirectionKey.equals("down")) {
                            displayDirection = "backward";
                        } else {
                            displayDirection = internalDirectionKey; // For "left", "right"
                        }
                        
                        PrintMethods.printColor(displayDirection + ": " + adjacentRoom.getName() + " ", textColor);
                    }
                }
            }
        }
    }

    private static float getRoomHue(Room room) {
        if (room instanceof StartKamer) {
            return 45f / 360f;
        } else if (room instanceof KamerScrumboard) {
            return 140f / 360f;
        } else if (room instanceof KamerReview) {
            return 240f / 360f;
        } else if (room instanceof KamerRetrospective) {
            return 275f / 360f;
        } else if (room instanceof KamerPlanning) {
            return 60f / 360f;
        } else if (room instanceof KamerDailyStandup) {
            return 35f / 360f;
        } else if (room instanceof EindKamer) {
            return 0f / 360f;
        }
        return 0.0f;
    }
}
