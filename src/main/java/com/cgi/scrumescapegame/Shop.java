package com.cgi.scrumescapegame;

import java.util.ArrayList;
import java.util.Scanner;

import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.cgi.scrumescapegame.items.Item;
import com.diogonunes.jcolor.Ansi;
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
            PrintMethods.printlnColor("Je hebt niet genoeg goud om " + itemToBuy.getName() + " te kopen!",
                    Attribute.BRIGHT_RED_TEXT());
            return;
        }
        player.changeGold(-itemPrice);
        player.addItem(itemToBuy);
    }

    public void sellItem(int playerItemIndex, Player player) {
        Item itemToSell = player.getItem(playerItemIndex);
        int sellPrice = itemToSell.getCurrentValue();
        if (sellPrice < 1) {
            PrintMethods.printlnColor("Je kan " + itemToSell.getName() + " niet verkopen.",
                    Attribute.BRIGHT_RED_TEXT());
            return;
        }

        player.changeGold(sellPrice);
        player.removeItem(playerItemIndex);
        PrintMethods.printlnColor("Je hebt " + itemToSell.getName() + " verkocht voor " + sellPrice + " goud.",
                Attribute.GREEN_TEXT());
        items.add(itemToSell);
    }

    public void interactiveMode(Scanner scanner, Player player) {
        showWelcome();
        boolean shopping = true;

        while (shopping) {
            showPlayerGold(player);
            showMenuOptions();
            String choice = readChoice(scanner);
            shopping = handleChoice(choice, scanner, player);
        }
    }

    private void showWelcome() {
        PrintMethods.typeText(
                Ansi.colorize(
                        "Welkom bij mijn shop, waar kan ik je mee helpen?",
                        Attribute.BRIGHT_GREEN_TEXT()));
    }

    private void showPlayerGold(Player player) {
        PrintMethods.printlnColor(
                "\nJe goud: " + player.getGold(),
                Attribute.YELLOW_TEXT());
    }

    private void showMenuOptions() {
        PrintMethods.printlnColor("------------------------", Attribute.BRIGHT_BLUE_TEXT());
        PrintMethods.printlnColor("Kies een actie:", Attribute.BRIGHT_BLUE_TEXT());
        PrintMethods.printlnColor("1. Koop items", Attribute.BRIGHT_BLUE_TEXT());
        PrintMethods.printlnColor("2. Verkoop items", Attribute.BRIGHT_BLUE_TEXT());
        PrintMethods.printlnColor("3. Verlaat de shop", Attribute.BRIGHT_BLUE_TEXT());
        PrintMethods.printColor(
                "(1/2/3) > ",
                Attribute.BRIGHT_BLUE_TEXT());
    }

    private String readChoice(Scanner scanner) {
        return scanner.nextLine().trim().toLowerCase();
    }

    /**
     * @return true to keep shopping, false to exit.
     */
    private boolean handleChoice(String choice, Scanner scanner, Player player) {
        switch (choice) {
            case "koop":
            case "1":
                handleBuy(scanner, player);
                return true;

            case "verkoop":
            case "2":
                handleSell(scanner, player);
                return true;

            case "verlaat":
            case "3":
                handleExit();
                return false;

            default:
                showInvalidChoice();
                return true;
        }
    }

    private void handleBuy(Scanner scanner, Player player) {
        if (items.isEmpty()) {
            PrintMethods.printlnColor("De shop is momenteel uit voorraad.", Attribute.YELLOW_TEXT());
            return;
        }

        PrintMethods.printlnColor("\n--- Items te koop ---", Attribute.CYAN_TEXT());
        for (int i = 0; i < items.size(); i++) {
            System.out.print((i + 1) + ". ");
            PrintMethods.printItem(items.get(i));
        }

        PrintMethods.printColor(
                "Typ het nummer in van het item dat je wilt kopen (of 'terug' om terug te gaan): ",
                Attribute.BRIGHT_BLUE_TEXT());
        String buyInput = scanner.nextLine().trim().toLowerCase();

        if (buyInput.equals("terug")) {
            return;
        }

        try {
            int itemNumber = Integer.parseInt(buyInput);
            int itemIndex = itemNumber - 1;

            if (itemIndex >= 0 && itemIndex < items.size()) {
                buyItem(itemIndex, player);
            } else {
                PrintMethods.printlnColor("Ongeldig item nummer.", Attribute.BRIGHT_RED_TEXT());
            }
        } catch (NumberFormatException e) {
            PrintMethods.printlnColor(
                    "Ongeldige input. Voeg een nummer toe of typ 'terug'.",
                    Attribute.BRIGHT_RED_TEXT());
        }
    }

    private void handleSell(Scanner scanner, Player player) {
        ArrayList<Item> playerItems = player.getItems();
        if (playerItems.isEmpty()) {
            PrintMethods.printlnColor("Je hebt niks om te verkopen.", Attribute.YELLOW_TEXT());
            return;
        }

        PrintMethods.printlnColor("\n--- Items te verkopen ---", Attribute.CYAN_TEXT());
        for (int i = 0; i < playerItems.size(); i++) {
            Item item = playerItems.get(i);
            if (item.getCurrentValue() < 1)
                continue;
            System.out.print((i + 1) + ". ");
            PrintMethods.printItem(item);
        }

        PrintMethods.printColor(
                "Typ het nummer in van het item dat je wilt verkopen (of 'terug' om terug te gaan): ",
                Attribute.BRIGHT_BLUE_TEXT());
        String sellInput = scanner.nextLine().trim().toLowerCase();

        if (sellInput.equals("terug")) {
            return;
        }

        try {
            int itemNumber = Integer.parseInt(sellInput);
            int playerItemIndex = itemNumber - 1;

            if (playerItemIndex >= 0 && playerItemIndex < playerItems.size()) {
                sellItem(playerItemIndex, player);
            } else {
                PrintMethods.printlnColor("Ongeldig item nummer.", Attribute.BRIGHT_RED_TEXT());
            }
        } catch (NumberFormatException e) {
            PrintMethods.printlnColor(
                    "Ongeldige input. Voeg een nummer toe of typ 'terug'.",
                    Attribute.BRIGHT_RED_TEXT());
        }
    }

    private void handleExit() {
        PrintMethods.typeTextColor(
                "Bedankt voor je bezoek! Kom snel weer terug!",
                Attribute.BRIGHT_GREEN_TEXT());
    }

    private void showInvalidChoice() {
        PrintMethods.printlnColor(
                "Ongeldige keuze. Kies van 'koop', 'verkoop', 'verlaat', of de bijbehorende getallen (1, 2, 3).",
                Attribute.BRIGHT_RED_TEXT());
    }

}