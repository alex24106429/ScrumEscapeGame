package com.cgi.scrumescapegame.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class TextToImageRenderer {

    private static Object[] prepareImageMetrics(String text, String fontFilename, int fontSize, int bottomPadding) {
        Font customFont;
        try (InputStream is = TextToImageRenderer.class.getResourceAsStream(fontFilename)) {
            if (is == null) {
                throw new IOException("Font file not found in classpath: " + fontFilename);
            }
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, is);
            customFont = baseFont.deriveFont(Font.PLAIN, (float) fontSize);
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException("Failed to load font: " + fontFilename + " (size: " + fontSize + ")", e);
        }

        BufferedImage tempImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D tempG2d = tempImage.createGraphics();
        tempG2d.setFont(customFont);
        FontMetrics fm = tempG2d.getFontMetrics();

        int ascent = fm.getAscent();
        int textRenderWidth = fm.stringWidth(text);
        int fontLineHeight = fm.getHeight();
        tempG2d.dispose();

        int imageWidth = Math.max(1, textRenderWidth);
        int imageHeight = Math.max(1, fontLineHeight + bottomPadding);

        return new Object[] { customFont, ascent, imageWidth, imageHeight, textRenderWidth, fontLineHeight };
    }

    /**
     * Renders the given text into a transparent BufferedImage with
     * a solid drop shadow (offset by 1,1) and a gradient‐filled text,
     * then prints it via ImagePrinter.
     *
     * @param text              The text to render and print.
     * @param fgColorStart      The start color for the text gradient.
     * @param fgColorEnd        The end color for the text gradient.
     * @param shadowColor       The solid color to use for the drop shadow.
     * @param fontIndex         Font index (1–5) as defined in FontConfig.
     * @param verticalGradient  True for vertical text gradient, false for horizontal.
     * @throws IllegalArgumentException if any Color parameter is null.
     * @throws RuntimeException         if the font cannot be loaded.
     */
    public static void printGradientTextWithShadow(
            String text,
            Color fgColorStart,
            Color fgColorEnd,
            Color shadowColor,
            int fontIndex,
            boolean verticalGradient
    ) {
        if (fgColorStart == null || fgColorEnd == null || shadowColor == null) {
            throw new IllegalArgumentException("Color parameters cannot be null.");
        }
        if (text == null || text.trim().isEmpty()) {
            System.err.println("Input text is null or empty. No image generated.");
            return;
        }

        // load font config
        Object[] fontConfig = FontConfig.getFontConfig();
        String fontFilename = (String) fontConfig[0];
        int fontSize = (Integer) fontConfig[1];
        int bottomPadding = (Integer) fontConfig[2];

        // measure text
        Object[] metrics = prepareImageMetrics(text, fontFilename, fontSize, bottomPadding);
        Font customFont       = (Font)    metrics[0];
        int  ascent           = (Integer) metrics[1];
        int  imageWidth       = (Integer) metrics[2];
        int  imageHeight      = (Integer) metrics[3];
        int  textRenderWidth  = (Integer) metrics[4];
        int  fontLineHeight   = (Integer) metrics[5];

        // create transparent image
        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setFont(customFont);

        // draw drop shadow (solid)
        g2d.setColor(shadowColor);
        g2d.drawString(text, 1, ascent + 1);

        // draw gradient text
        GradientPaint fgPaint;
        if (verticalGradient) {
            fgPaint = new GradientPaint(0, 0, fgColorStart, 0, fontLineHeight, fgColorEnd);
        } else {
            fgPaint = new GradientPaint(0, 0, fgColorStart, textRenderWidth, 0, fgColorEnd);
        }
        g2d.setPaint(fgPaint);
        g2d.drawString(text, 0, ascent);

        g2d.dispose();

        ImagePrinter.printBufferedImage(image);
    }
}