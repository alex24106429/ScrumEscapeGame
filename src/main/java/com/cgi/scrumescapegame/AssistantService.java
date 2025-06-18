package com.cgi.scrumescapegame;

import com.cgi.scrumescapegame.graphics.ImagePrinter;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.cgi.scrumescapegame.hints.FunnyHintProvider;
import com.cgi.scrumescapegame.items.*;
import com.diogonunes.jcolor.Attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class AssistantService {
    public static boolean hasBeenIntroduced = false;
    private static boolean keyAcquired = false;
    public static String imagePath = "assistent.png";
    
    private static final ArrayList<String> boodschappen = new ArrayList<>(Arrays.asList(
        "Je bent sterker dan je denkt.",
        "Elke val is een les.",
        "De uitgang is dichterbij dan je denkt.",
        "Je hebt dit al bijna gefikst!",
        "Durf verder te gaan.",
        "Schaduw vreest licht - en jouw!",
        "Je zwaard is scherp, net als jij.",
        "Monsters hebben ook angst... voor jou.",
        "Je tempo is goed, held!"
    ));

    private static final List<String> gameTips = new ArrayList<>(Arrays.asList(
        "Vergeet niet 'kijk rond' te gebruiken in een nieuwe kamer; je zou iets waardevols kunnen vinden!",
        "Houd de levensduur van je wapen en armor in de gaten. Een kapot item is nutteloos!",
        "Je DEF stat vermindert de schade die je oploopt. Een goed harnas kan het verschil maken.",
        "Geen goud? Verkoop overbodige items in de Shop of waag een gokje bij Blackjack.",
        "Battle Items zoals bommen kunnen een gevecht snel in jouw voordeel beslissen. Gebruik ze wijs!",
        "Level up! Het verslaan van vijanden geeft XP, wat je sterker maakt en je HP volledig herstelt.",
        "Een Fakkel in je inventaris vergroot je zicht op de map aanzienlijk."
    ));

    public static void activate(Player player) {
        ImagePrinter.printImage(imagePath);
        PrintMethods.typeTextColor("De assistent kijkt je aan. 'Hoe kan ik je van dienst zijn, reiziger?'", Attribute.BRIGHT_CYAN_TEXT());

        Scanner scanner = Game.scanner;
        boolean isInteracting = true;

        while (isInteracting) {
            PrintMethods.printlnColor("\n--- Assistent Menu ---", Attribute.BOLD());
            PrintMethods.printlnColor("1. Repareer een item", Attribute.BRIGHT_YELLOW_TEXT());
            PrintMethods.printlnColor("2. Upgrade een item", Attribute.BRIGHT_GREEN_TEXT());
            PrintMethods.printlnColor("3. Vraag om advies", Attribute.BRIGHT_BLUE_TEXT());
            PrintMethods.printlnColor("4. Verlaat", Attribute.BRIGHT_RED_TEXT());
            PrintMethods.printColor("> ", Attribute.CYAN_TEXT());

            String input = scanner.nextLine().trim();
            switch (input) {
                case "1":
                    handleRepair(player, scanner);
                    break;
                case "2":
                    handleUpgrade(player, scanner);
                    break;
                case "3":
                    showAdvice();
                    // Give key chance only when asking for advice
                    if (Randomizer.getRandomInt(1, 10) == 1 && !keyAcquired) {
                        keyAcquired = true;
                        player.addItem(new Key());
                    }
                    break;
                case "4":
                    isInteracting = false;
                    PrintMethods.typeTextColor("De assistent knikt. 'Wees voorzichtig daarbuiten.'", Attribute.BRIGHT_CYAN_TEXT());
                    break;
                default:
                    PrintMethods.printlnColor("Ongeldige keuze.", Attribute.RED_TEXT());
                    break;
            }
        }
    }

    private static void showAdvice() {
        // 1) Toon een hint
        FunnyHintProvider hintProv = new FunnyHintProvider();
        PrintMethods.printColor("Hint: ", Attribute.BRIGHT_MAGENTA_TEXT(), Attribute.BOLD());
        PrintMethods.typeTextColor(hintProv.getHint(), Attribute.MAGENTA_TEXT());

        // 2) Toon educatief hulpmiddel
        PrintMethods.printColor("Educatief Hulpmiddel: ", Attribute.BRIGHT_CYAN_TEXT(), Attribute.BOLD());
        String randomTip = gameTips.get(Randomizer.getRandomInt(gameTips.size()));
        PrintMethods.typeTextColor(randomTip, Attribute.CYAN_TEXT());

        // 4) Toon motiverende boodschap
        PrintMethods.printColor("Motivatie: ", Attribute.BRIGHT_YELLOW_TEXT(), Attribute.BOLD());
        int index = Randomizer.getRandomInt(0, boodschappen.size() - 1);
        PrintMethods.typeTextColor(boodschappen.get(index), Attribute.YELLOW_TEXT());
    }

    private static List<EquipableItem> getAllEquipableItems(Player player) {
        List<EquipableItem> allEquipables = new ArrayList<>();
        if (player.getWeapon() != null) {
            allEquipables.add(player.getWeapon());
        }
        if (player.getArmor() != null) {
            allEquipables.add(player.getArmor());
        }
        for (Item item : player.getItems()) {
            if (item instanceof EquipableItem) {
                allEquipables.add((EquipableItem) item);
            }
        }
        return allEquipables;
    }

    private static void handleRepair(Player player, Scanner scanner) {
        List<EquipableItem> allEquipables = getAllEquipableItems(player);
        List<EquipableItem> repairableItems = new ArrayList<>();

        for (EquipableItem item : allEquipables) {
            if (item.getCurrentDurability() < item.getMaxDurability()) {
                repairableItems.add(item);
            }
        }

        if (repairableItems.isEmpty()) {
            PrintMethods.printlnColor("Al je uitrusting is in perfecte staat!", Attribute.GREEN_TEXT());
            return;
        }

        PrintMethods.printlnColor("\n--- Items om te repareren ---", Attribute.BOLD());
        AtomicInteger index = new AtomicInteger(1);
        for (EquipableItem item : repairableItems) {
            int durabilityToRestore = item.getMaxDurability() - item.getCurrentDurability();
            int cost = durabilityToRestore * 2; // Cost calculation
            PrintMethods.printColor(index.getAndIncrement() + ". ", Attribute.CYAN_TEXT());
            PrintMethods.printColor(item.getName(), Attribute.WHITE_TEXT());
            PrintMethods.printColor(" (" + item.getCurrentDurability() + "/" + item.getMaxDurability() + ")", Attribute.YELLOW_TEXT());
            PrintMethods.printlnColor(" - Kosten: " + cost + "G", Attribute.YELLOW_TEXT());
        }
        PrintMethods.printlnColor("Je hebt " + player.getGold() + "G.", Attribute.YELLOW_TEXT());
        PrintMethods.printColor("Kies een item om te repareren (of 'terug'): ", Attribute.CYAN_TEXT());

        String input = scanner.nextLine().trim();
        if (input.equalsIgnoreCase("terug")) {
            return;
        }

        try {
            int choice = Integer.parseInt(input) - 1;
            if (choice >= 0 && choice < repairableItems.size()) {
                EquipableItem itemToRepair = repairableItems.get(choice);
                int durabilityToRestore = itemToRepair.getMaxDurability() - itemToRepair.getCurrentDurability();
                int cost = durabilityToRestore * 2;

                if (player.getGold() >= cost) {
                    player.changeGold(-cost);
                    itemToRepair.changeDurability(durabilityToRestore);
                    PrintMethods.printlnColor(itemToRepair.getName() + " is volledig gerepareerd voor " + cost + "G!", Attribute.BRIGHT_GREEN_TEXT());
                } else {
                    PrintMethods.printlnColor("Je hebt niet genoeg goud.", Attribute.RED_TEXT());
                }
            } else {
                PrintMethods.printlnColor("Ongeldig nummer.", Attribute.RED_TEXT());
            }
        } catch (NumberFormatException e) {
            PrintMethods.printlnColor("Ongeldige invoer.", Attribute.RED_TEXT());
        }
    }

    private static void handleUpgrade(Player player, Scanner scanner) {
        List<EquipableItem> allEquipables = getAllEquipableItems(player);

        if (allEquipables.isEmpty()) {
            PrintMethods.printlnColor("Je hebt geen uitrusting om te upgraden.", Attribute.YELLOW_TEXT());
            return;
        }

        PrintMethods.printlnColor("\n--- Items om te upgraden ---", Attribute.BOLD());
        AtomicInteger index = new AtomicInteger(1);
        for (EquipableItem item : allEquipables) {
            int cost = 50 * (item.getBuff() + 1); // Upgrade cost calculation
            PrintMethods.printColor(index.getAndIncrement() + ". ", Attribute.CYAN_TEXT());
            PrintMethods.printColor(item.getName(), Attribute.WHITE_TEXT());
            PrintMethods.printlnColor(" - Kosten: " + cost + "G", Attribute.YELLOW_TEXT());
        }
        PrintMethods.printlnColor("Je hebt " + player.getGold() + "G.", Attribute.YELLOW_TEXT());
        PrintMethods.printColor("Kies een item om te upgraden (of 'terug'): ", Attribute.CYAN_TEXT());

        String input = scanner.nextLine().trim();
        if (input.equalsIgnoreCase("terug")) {
            return;
        }

        try {
            int choice = Integer.parseInt(input) - 1;
            if (choice >= 0 && choice < allEquipables.size()) {
                EquipableItem itemToUpgrade = allEquipables.get(choice);
                int cost = itemToUpgrade.getCurrentValue() + 10 * (itemToUpgrade.getBuff() + 1);

                if (player.getGold() >= cost) {
                    player.changeGold(-cost);
                    
                    EquipableItem newVersion = createUpgradedItem(itemToUpgrade);
                    if (newVersion == null) {
                        PrintMethods.printlnColor("Dit item kan niet worden geüpgraded.", Attribute.RED_TEXT());
                        player.changeGold(cost); // Refund
                        return;
                    }

                    player.removeItem(itemToUpgrade);
                    player.addItemQuiet(newVersion);
                    PrintMethods.printlnColor("Je " + itemToUpgrade.getName() + " is geüpgraded naar " + newVersion.getName() + " voor " + cost + "G!", Attribute.BRIGHT_GREEN_TEXT());
                    PrintMethods.printlnColor("Het nieuwe item is in je inventaris geplaatst.", Attribute.DIM());

                } else {
                    PrintMethods.printlnColor("Je hebt niet genoeg goud.", Attribute.RED_TEXT());
                }
            } else {
                PrintMethods.printlnColor("Ongeldig nummer.", Attribute.RED_TEXT());
            }
        } catch (NumberFormatException e) {
            PrintMethods.printlnColor("Ongeldige invoer.", Attribute.RED_TEXT());
        }
    }

    private static EquipableItem createUpgradedItem(EquipableItem oldItem) {
        int newBuff = oldItem.getBuff() + 1;
        if (oldItem instanceof Sword) return new Sword(newBuff);
        if (oldItem instanceof GoldSword) return new GoldSword(newBuff);
        if (oldItem instanceof Shield) return new Shield(newBuff);
        if (oldItem instanceof Chestplate) return new Chestplate(newBuff);
        return null; // Not upgradable
    }

    public static void playIntroduction() {
        if (hasBeenIntroduced) return;
        ImagePrinter.printImage(imagePath);
        PrintMethods.typeTextColor("'Gegroet, reiziger.'", Attribute.CYAN_TEXT(), Attribute.ITALIC());
        PrintMethods.typeTextColor("'Ik ben de Assistent, een overblijfsel van de wil van de Architect, hier om je te helpen.'", Attribute.CYAN_TEXT(), Attribute.ITALIC());
        PrintMethods.typeTextColor("'Wanneer je mijn hulp nodig hebt, typ dan 'gebruik assistent'. Ik kan je uitrusting repareren, verbeteren, of je voorzien van advies.'", Attribute.CYAN_TEXT(), Attribute.ITALIC());
        hasBeenIntroduced = true;
    }
}