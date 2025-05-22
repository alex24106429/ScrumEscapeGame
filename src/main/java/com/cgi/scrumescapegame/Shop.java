package com.cgi.scrumescapegame;

import java.util.ArrayList;
import java.util.Scanner;

import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.cgi.scrumescapegame.items.Item;
import com.diogonunes.jcolor.Attribute;

public class Shop {
    private ArrayList<Item> items;

    public Shop(ArrayList<Item> initialItems) {
        this.items = initialItems;
    }

    public ArrayList<Item> getItems() {
        return new ArrayList<>(items);
    }

    public void buyItem(int itemIndex, Player player) {
        Item itemToBuy = items.get(itemIndex);
        int itemPrice = itemToBuy.getCurrentValue();

        if (player.getGold() < itemPrice) {
            PrintMethods.printlnColor("You don't have enough gold to buy " + itemToBuy.getName() + "!", Attribute.BRIGHT_RED_TEXT());
            return;
        }
        player.changeGold(-itemPrice);
        player.addItem(itemToBuy);
    }

    public void sellItem(int playerItemIndex, Player player) {
        Item itemToSell = player.getItem(playerItemIndex);
        int sellPrice = itemToSell.getCurrentValue();
        if(sellPrice < 1) {
            PrintMethods.printlnColor("You can't sell " + itemToSell.getName() + ".", Attribute.BRIGHT_RED_TEXT());
            return;
        }

        player.changeGold(sellPrice);
        player.removeItem(playerItemIndex);
        PrintMethods.printlnColor("You sold " + itemToSell.getName() + " for " + sellPrice + " gold.", Attribute.GREEN_TEXT());
        items.add(itemToSell);
    }

    public void interactiveMode(Scanner scanner, Player player) {
        PrintMethods.printlnColor("Welcome to the shop!", Attribute.BRIGHT_GREEN_TEXT());
        boolean shopping = true;

        while (shopping) {
            PrintMethods.printlnColor("\nYour Gold: " + player.getGold(), Attribute.YELLOW_TEXT());
            PrintMethods.printlnColor("------------------------", Attribute.BRIGHT_BLUE_TEXT());
            PrintMethods.printlnColor("Choose an action:", Attribute.BRIGHT_BLUE_TEXT());
            PrintMethods.printlnColor("1. Buy items", Attribute.BRIGHT_BLUE_TEXT());
            PrintMethods.printlnColor("2. Sell items", Attribute.BRIGHT_BLUE_TEXT());
            PrintMethods.printlnColor("3. Exit shop", Attribute.BRIGHT_BLUE_TEXT());
            PrintMethods.printColor("Enter your choice (buy/sell/exit or 1/2/3): ", Attribute.BRIGHT_BLUE_TEXT());
            String playerChoice = scanner.nextLine().trim().toLowerCase();

            switch (playerChoice) {
                case "buy":
                case "1":
                    if (items.isEmpty()) {
                        PrintMethods.printlnColor("The shop is currently out of stock.", Attribute.YELLOW_TEXT());
                        break;
                    }
                    PrintMethods.printlnColor("\n--- Items for Sale ---", Attribute.CYAN_TEXT());
                    for (int i = 0; i < items.size(); i++) {
                        System.out.print(String.format("%d. ", i + 1));
                        PrintMethods.printItem(items.get(i));
                    }
                    PrintMethods.printColor("Enter the number of the item you want to buy (or 'cancel' to go back): ", Attribute.BRIGHT_BLUE_TEXT());
                    String buyInput = scanner.nextLine().trim().toLowerCase();

                    if (buyInput.equals("cancel")) {
                        break;
                    }

                    try {
                        int itemNumber = Integer.parseInt(buyInput);
                        int itemIndex = itemNumber - 1;

                        if (itemIndex >= 0 && itemIndex < items.size()) {
                            buyItem(itemIndex, player);
                        } else {
                            PrintMethods.printlnColor("Invalid item number.", Attribute.BRIGHT_RED_TEXT());
                        }
                    } catch (NumberFormatException e) {
                        PrintMethods.printlnColor("Invalid input. Please enter a number or 'cancel'.", Attribute.BRIGHT_RED_TEXT());
                    }
                    break;

                case "sell":
                case "2":
                    ArrayList<Item> playerItems = player.getItems();
                    if (playerItems.isEmpty()) {
                        PrintMethods.printlnColor("You have no items to sell.", Attribute.YELLOW_TEXT());
                        break;
                    }

                    PrintMethods.printlnColor("\n--- Your Items to Sell ---", Attribute.CYAN_TEXT());
                    for (int i = 0; i < playerItems.size(); i++) {
                        Item item = playerItems.get(i);
                        if(item.getCurrentValue() < 1) continue;
                        System.out.print(String.format("%d. ", i + 1));
                        PrintMethods.printItem(item);
                    }
                    PrintMethods.printColor("Enter the number of the item you want to sell (or 'cancel' to go back): ", Attribute.BRIGHT_BLUE_TEXT());
                    String sellInput = scanner.nextLine().trim().toLowerCase();

                    if (sellInput.equals("cancel")) {
                        break;
                    }

                    try {
                        int itemNumber = Integer.parseInt(sellInput);
                        int playerItemIndex = itemNumber - 1;

                        if (playerItemIndex >= 0 && playerItemIndex < playerItems.size()) {
                            sellItem(playerItemIndex, player);
                        } else {
                            PrintMethods.printlnColor("Invalid item number.", Attribute.BRIGHT_RED_TEXT());
                        }
                    } catch (NumberFormatException e) {
                        PrintMethods.printlnColor("Invalid input. Please enter a number or 'cancel'.", Attribute.BRIGHT_RED_TEXT());
                    }
                    break;

                case "exit":
                case "3":
                    PrintMethods.printlnColor("Thank you for visiting the shop! Come again soon!", Attribute.BRIGHT_GREEN_TEXT());
                    shopping = false;
                    break;

                default:
                    PrintMethods.printlnColor("Invalid choice. Please enter 'buy', 'sell', 'exit', or their corresponding numbers (1, 2, 3).", Attribute.BRIGHT_RED_TEXT());
                    break;
            }
        }
    }
}