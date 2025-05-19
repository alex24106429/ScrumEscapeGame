package com.cgi.scrumescapegame;

import com.diogonunes.jcolor.Attribute;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items = new ArrayList<>();
    private Player player;

    public Inventory(Player player) {
        this.player = player;
    }

    public void addItem(Item item) {
        items.add(item);
        PrintMethods.printlnColor("Je hebt het item " + item.getName() + " gekregen!", Attribute.BRIGHT_GREEN_TEXT());
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void useItem(int itemIndex) {
        int index = itemIndex - 1;
        if (index < 0 || index >= items.size()) {
            PrintMethods.printlnColor("Ongeldig item nummer.", Attribute.RED_TEXT());
            return;
        }

        Item item = items.get(index);
        if (item instanceof UsableItem usable) {
            usable.useItem(player);
            if (usable.getUsesLeft() == 0) items.remove(index);
        } else if (item instanceof EquipableItem) {
            player.getEquipment().equip((EquipableItem) item);
            items.remove(index);
        } else {
            PrintMethods.printlnColor("Item kan niet gebruikt of uitgerust worden.", Attribute.RED_TEXT());
        }
    }

    public void printItems() {
        PlayerPrinter.printEquipped(player);
        PrintMethods.printlnColor("\nJe items:", Attribute.BOLD());
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            System.out.println((i + 1) + ": " + item.getName());
            PrintMethods.printlnColor(item.getDescription(), Attribute.BRIGHT_BLUE_TEXT());
            ImagePrinter.printImage(item.getImagepath());
        }
    }
}

