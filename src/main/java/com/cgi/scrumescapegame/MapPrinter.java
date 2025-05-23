package com.cgi.scrumescapegame;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

import com.cgi.scrumescapegame.graphics.ImagePrinter;
import com.cgi.scrumescapegame.kamers.EindKamer;
import com.cgi.scrumescapegame.kamers.KamerDailyStandup;
import com.cgi.scrumescapegame.kamers.KamerPlanning;
import com.cgi.scrumescapegame.kamers.KamerRetrospective;
import com.cgi.scrumescapegame.kamers.KamerReview;
import com.cgi.scrumescapegame.kamers.KamerScrumboard;
import com.cgi.scrumescapegame.kamers.StartKamer;

public class MapPrinter {
	public static void printMap(Player player) {
        int playerX = player.currentRoom.getCurrentPosition().x;
        int playerY = player.currentRoom.getCurrentPosition().y;

        int roomExtentXMin = Integer.MAX_VALUE;
        int roomExtentXMax = Integer.MIN_VALUE;
        int roomExtentYMin = Integer.MAX_VALUE;
        int roomExtentYMax = Integer.MIN_VALUE;

        for (Point p : Map.positions) {
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
                } else if (Map.hasRoom(worldX, worldY)) {
                    // Room tile: determine color based on room type and distance
                    Room currentRoom = Map.getRoomAt(worldX, worldY, Game.rooms);
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

                        int distance = Math.abs(playerX - worldX) + Math.abs(playerY - worldY);
                        
                        if (distance == 1) {
                            roomColor = Color.getHSBColor(hue, 0.5f, 0.4f);
                        } else if (distance == 2) {
                            roomColor = Color.getHSBColor(hue, 0.5f, 0.2f);
                        } else if (Game.debug) {
                            roomColor = Color.getHSBColor(hue, 0.5f, 0.2f);
                        } else {
                            roomColor = Color.BLACK;
                        }
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
                    if (Map.hasRoom(worldX, worldY + 1)) {
                        pixelBlockColors[0] = outlineColor; // Top-left pixel of current empty tile
                        pixelBlockColors[1] = outlineColor; // Top-right pixel of current empty tile
                    }
                    // Check South neighbor (worldY - 1)
                    if (Map.hasRoom(worldX, worldY - 1)) {
                        pixelBlockColors[2] = outlineColor; // Bottom-left pixel
                        pixelBlockColors[3] = outlineColor; // Bottom-right pixel
                    }
                    // Check East neighbor (worldX + 1)
                    if (Map.hasRoom(worldX + 1, worldY)) {
                        pixelBlockColors[1] = outlineColor; // Top-right pixel
                        pixelBlockColors[3] = outlineColor; // Bottom-right pixel
                    }
                    // Check West neighbor (worldX - 1)
                    if (Map.hasRoom(worldX - 1, worldY)) {
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
}
