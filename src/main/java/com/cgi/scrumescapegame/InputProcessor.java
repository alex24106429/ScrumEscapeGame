package com.cgi.scrumescapegame;

import java.awt.Point;
import java.util.Objects;
import java.util.Scanner;

import com.cgi.scrumescapegame.graphics.MapPrinter;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.diogonunes.jcolor.Attribute;

public class InputProcessor {
    public static void processInput(String input, Player player, Game game, Scanner scanner, GameMap map) {
        if (input.startsWith("go ")) {
            String direction = input.substring("go ".length()).trim();
            Room currentRoom = player.getCurrentRoom();
            Point currentPos = currentRoom.getCurrentPosition();
            int targetX = currentPos.x;
            int targetY = currentPos.y;
            boolean moved = false;

            switch (direction) {
                case "forward":
                case "up":
                    if (currentRoom.adjacentRooms.getOrDefault("up", false)) {
                        targetY++;
                        moved = true;
                    } else {
                        System.out.println("You cannot go forward from here.");
                    }
                    break;
                case "backward":
                case "down":
                    if (currentRoom.adjacentRooms.getOrDefault("down", false)) {
                        targetY--;
                        moved = true;
                    } else {
                        System.out.println("You cannot go backward from here.");
                    }
                    break;
                case "left":
                    if (currentRoom.adjacentRooms.getOrDefault("left", false)) {
                        targetX--;
                        moved = true;
                    } else {
                        System.out.println("You cannot go left from here.");
                    }
                    break;
                case "right":
                    if (currentRoom.adjacentRooms.getOrDefault("right", false)) {
                        targetX++;
                        moved = true;
                    } else {
                        System.out.println("You cannot go right from here.");
                    }
                    break;
                default:
                    System.out.println("Invalid direction. Use forward, backward, left, or right.");
                    break;
            }

            if (moved) {
                Point targetPos = new Point(targetX, targetY);
                Room nextRoom = null;
                for (Room room : Game.rooms) {
                    if (Objects.equals(room.getCurrentPosition(), targetPos)) {
                        nextRoom = room;
                        break;
                    }
                }

                if (nextRoom != null) {
                    player.setCurrentRoom(nextRoom);
                    nextRoom.enterRoom(player);
                    MapPrinter.printMap(player, map);
                } else {
                    // This case should ideally not happen if adjacentRooms is correctly set
                    System.out.println("Error: Could not find the next room.");
                }
            }

        } else if (input.startsWith("gebruik item ")) {
            try {
                String itemNumberStr = input.substring("gebruik item ".length()).trim();
                int itemNumber = Integer.parseInt(itemNumberStr);
                player.useItem(itemNumber - 1);
            } catch (NumberFormatException e) {
                System.out.println("Ongeldig itemnummer. Gebruik bijvoorbeeld 'gebruik item 1'.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Die item bestaat niet. Typ 'items' om beschikbare items te zien.");
            }
        } else if (input.equals("unequip armor")) {
            player.unequipArmor();
        } else if (input.equals("unequip weapon")) {
            player.unequipWeapon();
        } else if (input.equals("status")) {
            player.printStatus();
        } else if (input.equals("kijk rond")) {
            player.kijkRond();
        } else if (input.equals("items")) {
            player.printItems();
        } else if (input.equals("opslaan")) {
            game.saveGame();
        } else if (input.equals("help")) {
            GamePrints.printHelp();
        } else if (input.equals("quit")) {
            if (Game.debug) System.exit(0);

            PrintMethods.printlnColor("Wil je opslaan? ja/nee", Attribute.BRIGHT_RED_TEXT());
            String option = scanner.nextLine();
            if (option.equals("ja")) {
                game.saveGame();
            }
            System.exit(0);
        } else {
            System.out.println("Onbekend commando. Typ 'help' voor een lijst met commando's.");
        }
    }
}
