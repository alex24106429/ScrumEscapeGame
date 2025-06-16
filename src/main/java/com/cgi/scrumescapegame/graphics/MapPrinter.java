package com.cgi.scrumescapegame.graphics;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import java.util.Map;

import com.cgi.scrumescapegame.Game;
import com.cgi.scrumescapegame.GameMap;
import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.items.Torch;
import com.cgi.scrumescapegame.kamers.Room;
import com.diogonunes.jcolor.Attribute;

public class MapPrinter {
	public static void printMap(Player player, GameMap gameMap) {
        // 1) Figure out player coords
        Point playerPos = player.getCurrentRoom().getCurrentPosition();
        int playerX = playerPos.x;
        int playerY = playerPos.y;

        // 2) Compute room extents and draw‐area
        int[] extents = calculateRoomExtents(gameMap);
        int[] draw   = calculateDrawArea(extents[0], extents[1], extents[2], extents[3]);
        int drawMinX = draw[0], drawMaxX = draw[1], drawMinY = draw[2], drawMaxY = draw[3];

        // 3) Validate & create image
        int logicalWidth  = drawMaxX - drawMinX + 1;
        int logicalHeight = drawMaxY - drawMinY + 1;
        if (!validateDimensions(logicalWidth, logicalHeight)) {
            System.out.println("Invalid map dimensions calculated. Cannot print map.");
            return;
        }
        BufferedImage mapImage = new BufferedImage(
            logicalWidth * 2,
            logicalHeight * 2,
            BufferedImage.TYPE_INT_ARGB
        );

        // 4) Prepare colors & player‐rect
        int playerColor     = computePlayerColor();
        int emptySpaceColor = computeEmptySpaceColor();
        int outlineColor    = computeOutlineColor();
        double[] playerRect = calculatePlayerPixelRect(playerX, playerY, drawMinX, drawMaxY);

        // 5) Render every tile
        renderMapTiles(
        mapImage, player, gameMap,
        drawMinX, drawMaxX, drawMinY, drawMaxY,
        playerX, playerY,
        playerColor, emptySpaceColor, outlineColor,
        playerRect
        );

        // 6) Show it & footer
        ImagePrinter.printBufferedImage(mapImage);
        printMapFooter(player);
    }


    //-----------------------------------------------------------------------------
    //  Helpers
    //-----------------------------------------------------------------------------

    // 2a) Find bounding box of all rooms
    private static int[] calculateRoomExtents(GameMap gameMap) {
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
        for (Point p : gameMap.getPositions()) {
            minX = Math.min(minX, p.x);
            maxX = Math.max(maxX, p.x);
            minY = Math.min(minY, p.y);
            maxY = Math.max(maxY, p.y);
        }
        return new int[]{ minX, maxX, minY, maxY };
    }

    // 2b) Expand that box by one tile all around
    private static int[] calculateDrawArea(int roomMinX, int roomMaxX, int roomMinY, int roomMaxY) {
        return new int[]{
            roomMinX - 1,  roomMaxX + 1,
            roomMinY - 1,  roomMaxY + 1
        };
    }

    // 3) Make sure we don’t get crazy
    private static boolean validateDimensions(int w, int h) {
        return w > 0 && h > 0;
    }

    // 4a) Colors
    private static int computePlayerColor() {
        return Color.HSBtoRGB(0, 0.0f, 1.0f);
    }
    private static int computeEmptySpaceColor() {
        return 0x00000000;
    }
    private static int computeOutlineColor() {
        return Color.HSBtoRGB(0f, 0f, 0.5f);
    }

    // 4b) Player’s pixel rectangle in image coords
    //    [0]=x1, [1]=y1, [2]=x2, [3]=y2
    private static double[] calculatePlayerPixelRect(
        int playerX, int playerY,
        int drawMinX, int drawMaxY
    ) {
        int pxMin = (playerX - drawMinX) * 2;
        int pyMin = (drawMaxY - playerY) * 2;
        return new double[]{ pxMin, pyMin, pxMin + 2.0, pyMin + 2.0 };
    }

    // 5) Walk every world‐tile and dispatch to the right drawer
    private static void renderMapTiles(
        BufferedImage mapImage,
        Player player,
        GameMap gameMap,
        int drawMinX, int drawMaxX, int drawMinY, int drawMaxY,
        int playerX, int playerY,
        int playerColor, int emptyColor, int outlineColor,
        double[] playerRect
    ) {
        for (int worldY = drawMaxY; worldY >= drawMinY; worldY--) {
            for (int worldX = drawMinX; worldX <= drawMaxX; worldX++) {
                int imgX = (worldX - drawMinX) * 2;
                int imgY = (drawMaxY - worldY) * 2;

                if (worldX == playerX && worldY == playerY) {
                    drawPlayerTile(mapImage, imgX, imgY, playerColor);
                }
                else if (gameMap.hasRoom(worldX, worldY)) {
                    drawRoomTile(
                    mapImage, player, worldX, worldY,
                    imgX, imgY, playerRect
                    );
                }
                else {
                    drawEmptyTile(
                    mapImage, gameMap,
                    worldX, worldY,
                    imgX, imgY,
                    emptyColor, outlineColor
                    );
                }
            }
        }
    }

    // 5a) The 2×2 player marker
    private static void drawPlayerTile(BufferedImage img, int x, int y, int color) {
        img.setRGB(x,     y,     color);
        img.setRGB(x + 1, y,     color);
        img.setRGB(x,     y + 1, color);
        img.setRGB(x + 1, y + 1, color);
    }

    // 5b) A “real” room: hue + distance‐based darkness
    private static void drawRoomTile(
        BufferedImage img,
        Player player,
        int worldX,
        int worldY,
        int imgX,
        int imgY,
        double[] playerRect
    ) {
        Room room = GameMap.getRoomAt(worldX, worldY, Game.rooms);
        if (room == null) return;

        float hue = getRoomHue(room);
        boolean hasTorch = player.getItems()
                                .stream()
                                .anyMatch(i -> i instanceof Torch);

        for (int py = 0; py < 2; py++) {
            for (int px = 0; px < 2; px++) {
                int pixelX = imgX + px;
                int pixelY = imgY + py;
                double centerX = pixelX + 0.5;
                double centerY = pixelY + 0.5;
                double dist    = computeDistanceToPlayerRect(centerX, centerY, playerRect);
                float brightness = computeBrightness(dist, hasTorch);
                int rgb;
                if(!room.getCleared()) {
                    rgb = Color.getHSBColor(hue, 0.5f, brightness).getRGB();
                } else {
                    rgb = Color.getHSBColor(hue, 0.1f, brightness).getRGB();
                }
                img.setRGB(pixelX, pixelY, rgb);
            }
        }
    }

    // 5b-i) distance from pixel center to the player’s 2×2 rect
    private static double computeDistanceToPlayerRect(
        double cx, double cy,
        double[] rect
    ) {
        double x1 = rect[0], y1 = rect[1], x2 = rect[2], y2 = rect[3];
        double closestX = Math.max(x1, Math.min(cx, x2));
        double closestY = Math.max(y1, Math.min(cy, y2));
        double dx = cx - closestX, dy = cy - closestY;
        return Math.sqrt(dx*dx + dy*dy);
    }

    // 5b-ii) light falloff (torch doubles reach & brightness)
    private static float computeBrightness(double dist, boolean hasTorch) {
        float minD = 1.0f;
        float fullB = hasTorch ? 0.8f : 0.5f;
        float maxD  = hasTorch ? 8.0f : 4.0f;
        float norm = Math.max(0f, (float)((dist - minD)/(maxD - minD)));
        float f    = (float)Math.pow(Math.max(0f, 1f - norm), 2.0f);
        return Math.max(0f, fullB * f);
    }

    // 5c) empty tile + outline on any neighbor rooms
    private static void drawEmptyTile(
        BufferedImage img,
        GameMap gameMap,
        int worldX,
        int worldY,
        int imgX,
        int imgY,
        int emptyColor,
        int outlineColor
    ) {
        int[] c = { emptyColor, emptyColor, emptyColor, emptyColor };
        if (gameMap.hasRoom(worldX, worldY + 1)) { c[0] = outlineColor; c[1] = outlineColor; }
        if (gameMap.hasRoom(worldX, worldY - 1)) { c[2] = outlineColor; c[3] = outlineColor; }
        if (gameMap.hasRoom(worldX + 1, worldY)) { c[1] = outlineColor; c[3] = outlineColor; }
        if (gameMap.hasRoom(worldX - 1, worldY)) { c[0] = outlineColor; c[2] = outlineColor; }
        img.setRGB(imgX,     imgY,     c[0]);
        img.setRGB(imgX + 1, imgY,     c[1]);
        img.setRGB(imgX,     imgY + 1, c[2]);
        img.setRGB(imgX + 1, imgY + 1, c[3]);
    }

    // 6) footer exactly as before
    private static void printMapFooter(Player player) {
        if (player.getCurrentRoom().getCleared()) {
            printAvailableRooms(player);
        } else {
            PrintMethods.printColor(
                "De deuren zijn gesloten. Maak de puzzel af (\"start puzzel\") om de kamer te kunnen verlaten.",
                Attribute.BRIGHT_YELLOW_TEXT()
            );
        }
    }


    private static void printAvailableRooms(Player player) {
        int playerX = player.currentRoom.getCurrentPosition().x;
        int playerY = player.currentRoom.getCurrentPosition().y;

        PrintMethods.printColor("Beschikbare kamers: ", Attribute.BOLD());
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
                        Color roomColor = Color.getHSBColor(hue, 0.5f, 0.8f);
                        Attribute textColor = Attribute.TEXT_COLOR(roomColor.getRed(), roomColor.getGreen(), roomColor.getBlue());

                        String displayDirection;
                        if (internalDirectionKey.equals("up")) {
                            displayDirection = "vooruit";
                        } else if (internalDirectionKey.equals("down")) {
                            displayDirection = "achteruit";
                        } else if (internalDirectionKey.equals("left")) {
                            displayDirection = "links";
                        } else if (internalDirectionKey.equals("right")) {
                            displayDirection = "rechts";
                        } else {
                            displayDirection = internalDirectionKey; // Fallback for unexpected keys
                        }
                        
                        PrintMethods.printColor(displayDirection + ": " + adjacentRoom.getName() + " ", textColor);
                    }
                }
            }
        }
        System.out.println();
    }

    private static float getRoomHue(Room room) {
        return (float) room.getHue() / 360f;
    }
}
