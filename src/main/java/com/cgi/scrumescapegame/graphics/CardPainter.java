package com.cgi.scrumescapegame.graphics;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public final class CardPainter {

    private static final BufferedImage SHEET;
    static {
        try { SHEET = ImageIO.read(CardPainter.class.getResource("/cardcomponents.png")); }
        catch (Exception e) { throw new RuntimeException(e); }
    }

    /** rank: 0-12 (A…K)  suit: 0-3 (♥♦♣♠)  back=true → back side */
    public static BufferedImage draw(int rank, int suit, boolean back) {
        BufferedImage card = new BufferedImage(19, 28, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = card.createGraphics();

        // full front / back
        g.drawImage(SHEET.getSubimage(back ? 19 : 0, 0, 19, 27), 0, 0, null);
        if (back) { g.dispose(); return card; }

        // small sprites
        BufferedImage sym = SHEET.getSubimage(suit * 11, 27, 11, 11);     // suit
        BufferedImage chr = SHEET.getSubimage(rank * 5, 38, 5, 5);        // rank

        // 1) normal rank, top-left
        g.drawImage(chr, 3, 3, null);

        // 2) upside-down rank, bottom-right
        AffineTransform at = new AffineTransform();
        at.translate(11, 19);              // bottom-right corner
        at.rotate(Math.PI, 2.5, 2.5);      // rotate 180° around sprite centre
        g.drawImage(chr, at, null);

        // 3) centre suit
        g.drawImage(sym, 4, 8, null);      // centred: (19-11)/2 , (27-11)/2

        g.dispose();
        return card;
    }
}