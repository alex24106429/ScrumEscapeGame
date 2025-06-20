package com.cgi.scrumescapegame.minigames;

import com.cgi.scrumescapegame.Player;
import com.cgi.scrumescapegame.graphics.CardPrinter;
import com.cgi.scrumescapegame.graphics.ImagePrinter;
import com.cgi.scrumescapegame.graphics.PrintMethods;
import com.diogonunes.jcolor.Attribute;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BlackjackMinigame implements Minigame {
    private boolean success;
    private static final int CARD_MARGIN = 1;

    @Override public String getName() { return "Blackjack"; }
    @Override public String getDescription() {
        return "Versla de dealer door een handwaarde zo dicht mogelijk bij 21 te krijgen zonder eroverheen te gaan.";
    }

    @Override
    public void startMinigame(Player player, Scanner scanner) {
        int bet = 0;
        int playerGold = player.getGold();
        if(playerGold < 1) {
            PrintMethods.typeTextColor("Je hebt minstens 1 Goud nodig om Blackjack te spelen, kom later terug!", Attribute.BRIGHT_RED_TEXT());
            return;
        }
        PrintMethods.printColor("Hoeveel Goud zet je in? (1 tot " + playerGold + "): ", Attribute.CYAN_TEXT());
        while (true) {
            try {
                bet = Integer.parseInt(scanner.nextLine());
                if (bet >= 1 && bet <= playerGold) {
                    break;
                }
            } catch (NumberFormatException e) {}
            PrintMethods.printlnColor("Ongeldig inzetbedrag. Voer een waarde in tussen 1 en " + playerGold + ".", Attribute.BRIGHT_RED_TEXT());
        }
        player.changeGold(-bet);

        List<Integer> deck = new ArrayList<>();
        for (int r = 0; r < 13; r++)
            for (int s = 0; s < 4; s++)
                deck.add(r * 4 + s);
        Collections.shuffle(deck);

        List<Integer> dealer = new ArrayList<>();
        List<Integer> hand = new ArrayList<>();
        hand.add(deck.remove(0)); hand.add(deck.remove(0));
        dealer.add(deck.remove(0)); dealer.add(deck.remove(0));

        PrintMethods.printlnColor("De dealer laat zien:", Attribute.YELLOW_TEXT());
        printDealerInitial(dealer);
        System.out.println("Waarde: " + value(dealer.subList(0,1)));

        PrintMethods.printlnColor("Jouw hand:", Attribute.GREEN_TEXT());
        printHand(hand);
        System.out.println("Waarde: " + value(hand));

        while (value(hand) < 21) {
            PrintMethods.typeTextColor("Hit of stand? (h/s): ", Attribute.CYAN_TEXT());
            String inp = scanner.nextLine();
            if ("h".equalsIgnoreCase(inp)) {
                hand.add(deck.remove(0));
                printHand(Collections.singletonList(hand.get(hand.size() - 1)));
                System.out.println("Waarde: " + value(hand));
            } else break;
        }

        int playerVal = value(hand);
        // Reveal dealer hand
        PrintMethods.printlnColor("Dealer's beurt:", Attribute.YELLOW_TEXT());
        printHand(dealer);
        while (value(dealer) < 17) {
            dealer.add(deck.remove(0));
            printHand(Collections.singletonList(dealer.get(dealer.size() - 1)));
        }

        int dealerVal = value(dealer);
        System.out.println("Jouw totaal: " + playerVal + ", Dealer's totaal: " + dealerVal);
        success = playerVal <= 21 && (dealerVal > 21 || playerVal > dealerVal);
        if (success) {
            PrintMethods.printlnColor("Je wint!", Attribute.BRIGHT_GREEN_TEXT());
            player.changeGold(bet * 2);
        } else {
            PrintMethods.printlnColor("Je verliest!", Attribute.BRIGHT_RED_TEXT());
        }
    }

    @Override public boolean isSuccessful() { return success; }

    private int value(List<Integer> hand) {
        int sum = 0, aces = 0;
        for (int card : hand) {
            int rank = card / 4;
            if (rank == 0) { sum += 11; aces++; }
            else if (rank >= 10) sum += 10;
            else sum += rank + 1;
        }
        while (sum > 21 && aces-- > 0) sum -= 10;
        return sum;
    }

    private void printDealerInitial(List<Integer> dealer) {
        BufferedImage up = CardPrinter.draw(dealer.get(0) / 4, dealer.get(0) % 4, false);
        BufferedImage down = CardPrinter.draw(dealer.get(1) / 4, dealer.get(1) % 4, true);
        BufferedImage combined = combine(List.of(up, down));
        ImagePrinter.printBufferedImage(combined);
    }

    private void printHand(List<Integer> hand) {
        List<BufferedImage> imgs = new ArrayList<>();
        for (int c : hand)
            imgs.add(CardPrinter.draw(c / 4, c % 4, false));
        BufferedImage combined = combine(imgs);
        ImagePrinter.printBufferedImage(combined);
    }

    private BufferedImage combine(List<BufferedImage> imgs) {
        int width = CARD_MARGIN * (imgs.size() - 1);
        int height = 0;
        for (BufferedImage img : imgs) {
            width += img.getWidth();
            height = Math.max(height, img.getHeight());
        }
        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = out.getGraphics();
        int x = 0;
        for (BufferedImage img : imgs) {
            g.drawImage(img, x, 0, null);
            x += img.getWidth() + CARD_MARGIN;
        }
        g.dispose();
        return out;
    }
}
