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

//    /**
//     * Renders the given text into a BufferedImage using a predefined font, size,
//     * and custom bottom padding specified by fontIndex, and then prints this image
//     * using ImagePrinter.
//     *
//     * @param text      The text to render and print.
//     * @param fgColor   The foreground (text) color.
//     * @param bgColor   The background color.
//     * @param fontIndex An integer code specifying the font, size, and bottom
//     *                  padding:
//     *                  1: "GravityRegular5.ttf", Size 5, Bottom Padding 2 (Note:
//     *                  code uses 1)
//     *                  2: "hero-speak.ttf", Size 14, Bottom Padding -4 (Note: code
//     *                  uses -3)
//     *                  3: "GravityBold8.ttf", Size 8, Bottom Padding 0
//     *                  4: "fibberish.ttf", Size 16, Bottom Padding -8 (Note: code
//     *                  uses -7)
//     *                  5: "antiquity-print.ttf", Size 13, Bottom Padding 0
//     * @param halfWidth A boolean that controls if the text is printed at half the
//     *                  width using quadrant characters.
//     * @throws IllegalArgumentException if fontIndex is not one of the defined
//     *                                  values (1-5).
//     * @throws RuntimeException         if the selected font file cannot be found or
//     *                                  loaded during image creation.
//     */
//    public static void printText(String text, Color fgColor, Color bgColor, int fontIndex, boolean halfWidth) {
//        Object[] fontConfig = FontConfig.getFontConfig(fontIndex);
//        String selectedFontFilename = (String) fontConfig[0];
//        int selectedFontSize = (Integer) fontConfig[1];
//        int selectedBottomPadding = (Integer) fontConfig[2];
//
//        BufferedImage imageToPrint;
//
//        if (text == null || text.trim().isEmpty()) {
//            System.err.println("Input text is null or empty. Printing a minimal 1x1 image.");
//            return;
//        } else {
//            Object[] metrics = prepareImageMetrics(text, selectedFontFilename, selectedFontSize, selectedBottomPadding);
//            Font customFont = (Font) metrics[0];
//            int ascent = (Integer) metrics[1];
//            int imageWidth = (Integer) metrics[2];
//            int imageHeight = (Integer) metrics[3];
//
//            BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
//            Graphics2D g2d = image.createGraphics();
//
//            g2d.setColor(bgColor);
//            g2d.fillRect(0, 0, imageWidth, imageHeight);
//
//            g2d.setFont(customFont);
//            g2d.setColor(fgColor);
//            g2d.drawString(text, 0, ascent);
//
//            g2d.dispose();
//            imageToPrint = image;
//        }
//        if (halfWidth) {
//            ImagePrinter.printBufferedImageQuadrant(imageToPrint);
//        } else {
//            ImagePrinter.printBufferedImage(imageToPrint);
//        }
//    }

//    /**
//     * Renders the given text into a BufferedImage with gradient foreground and
//     * background,
//     * using a predefined font, size, and custom bottom padding specified by
//     * fontIndex,
//     * and then prints this image using ImagePrinter.
//     *
//     * @param text             The text to render and print.
//     * @param fgColorStart     The starting color for the foreground (text)
//     *                         gradient.
//     * @param fgColorEnd       The ending color for the foreground (text) gradient.
//     * @param bgColorStart     The starting color for the background gradient.
//     * @param bgColorEnd       The ending color for the background gradient.
//     * @param fontIndex        An integer code specifying the font, size, and bottom
//     *                         padding:
//     *                         1: "GravityRegular5.ttf", Size 5, Bottom Padding 1
//     *                         2: "hero-speak.ttf", Size 14, Bottom Padding -3
//     *                         3: "GravityBold8.ttf", Size 8, Bottom Padding 0
//     *                         4: "fibberish.ttf", Size 16, Bottom Padding -7
//     *                         5: "antiquity-print.ttf", Size 13, Bottom Padding 0
//     * @param verticalGradient If true, gradients are vertical (top to bottom).
//     *                         If false, gradients are horizontal (left to right).
//     * @param halfWidth        A boolean that controls if the text is printed at
//     *                         half the width using quadrant characters.
//     * @throws IllegalArgumentException if fontIndex is not one of the defined
//     *                                  values (1-5),
//     *                                  or if any Color parameter is null.
//     * @throws RuntimeException         if the selected font file cannot be found or
//     *                                  loaded during image creation.
//     */
//    public static void printGradientText(String text, Color fgColorStart, Color fgColorEnd, Color bgColorStart,
//            Color bgColorEnd, int fontIndex, boolean verticalGradient, boolean halfWidth) {
//        if (fgColorStart == null || fgColorEnd == null || bgColorStart == null || bgColorEnd == null) {
//            throw new IllegalArgumentException("Color parameters cannot be null.");
//        }
//
//        Object[] fontConfig = FontConfig.getFontConfig(fontIndex);
//        String selectedFontFilename = (String) fontConfig[0];
//        int selectedFontSize = (Integer) fontConfig[1];
//        int selectedBottomPadding = (Integer) fontConfig[2];
//
//        BufferedImage imageToPrint;
//
//        if (text == null || text.trim().isEmpty()) {
//            System.err.println("Input text is null or empty. Printing a minimal 1x1 image with bgColorStart.");
//            return;
//        } else {
//            Object[] metrics = prepareImageMetrics(text, selectedFontFilename, selectedFontSize, selectedBottomPadding);
//            Font customFont = (Font) metrics[0];
//            int ascent = (Integer) metrics[1];
//            int imageWidth = (Integer) metrics[2];
//            int imageHeight = (Integer) metrics[3];
//            int textRenderWidth = (Integer) metrics[4];
//            int fontLineHeight = (Integer) metrics[5];
//
//            BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
//            Graphics2D g2d = image.createGraphics();
//
//            GradientPaint bgPaint;
//            if (verticalGradient) {
//                bgPaint = new GradientPaint(0, 0, bgColorStart, 0, imageHeight, bgColorEnd);
//            } else {
//                bgPaint = new GradientPaint(0, 0, bgColorStart, imageWidth, 0, bgColorEnd);
//            }
//            g2d.setPaint(bgPaint);
//            g2d.fillRect(0, 0, imageWidth, imageHeight);
//
//            g2d.setFont(customFont);
//
//            GradientPaint fgPaint;
//            if (verticalGradient) {
//                fgPaint = new GradientPaint(0, 0, fgColorStart, 0, fontLineHeight, fgColorEnd);
//            } else {
//                fgPaint = new GradientPaint(0, 0, fgColorStart, textRenderWidth, 0, fgColorEnd);
//            }
//            g2d.setPaint(fgPaint);
//            g2d.drawString(text, 0, ascent);
//
//            g2d.dispose();
//            imageToPrint = image;
//        }
//        if (halfWidth) {
//            ImagePrinter.printBufferedImageQuadrant(imageToPrint);
//        } else {
//            ImagePrinter.printBufferedImage(imageToPrint);
//        }
//    }

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
     * @param halfWidth         True to print at half-width using quadrant chars.
     * @throws IllegalArgumentException if any Color parameter is null.
     * @throws RuntimeException         if the font cannot be loaded.
     */
    public static void printGradientTextWithShadow(
            String text,
            Color fgColorStart,
            Color fgColorEnd,
            Color shadowColor,
            int fontIndex,
            boolean verticalGradient,
            boolean halfWidth
    ) {
        if (fgColorStart == null || fgColorEnd == null || shadowColor == null) {
            throw new IllegalArgumentException("Color parameters cannot be null.");
        }
        if (text == null || text.trim().isEmpty()) {
            System.err.println("Input text is null or empty. No image generated.");
            return;
        }

        // load font config
        Object[] fontConfig = FontConfig.getFontConfig(fontIndex);
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

        // print
        if (halfWidth) {
            ImagePrinter.printBufferedImageQuadrant(image);
        } else {
            ImagePrinter.printBufferedImage(image);
        }
    }

//    /**
//     * Renders the given text into a transparent BufferedImage with
//     * a 1 px outline all around it (in outlineColor) and a gradient‐filled text,
//     * then prints it via ImagePrinter.
//     *
//     * @param text              The text to render and print.
//     * @param fgColorStart      The starting color for the text gradient.
//     * @param fgColorEnd        The ending color for the text gradient.
//     * @param outlineColor      The solid color to use for the 1 px outline.
//     * @param fontIndex         Font index (1–5) as defined in FontConfig.
//     * @param verticalGradient  True for vertical text gradient, false for horizontal.
//     * @param halfWidth         True to print at half-width using quadrant chars.
//     * @throws IllegalArgumentException if any Color parameter is null.
//     * @throws RuntimeException         if the font cannot be loaded.
//     */
//    public static void printGradientTextWithOutline(
//            String text,
//            Color fgColorStart,
//            Color fgColorEnd,
//            Color outlineColor,
//            int fontIndex,
//            boolean verticalGradient,
//            boolean halfWidth
//    ) {
//        // Validate
//        if (fgColorStart == null || fgColorEnd == null || outlineColor == null) {
//            throw new IllegalArgumentException("Color parameters cannot be null.");
//        }
//        if (text == null || text.trim().isEmpty()) {
//            System.err.println("Input text is null or empty. No image generated.");
//            return;
//        }
//
//        // Load font config
//        Object[] fontConfig = FontConfig.getFontConfig(fontIndex);
//        String fontFilename   = (String)  fontConfig[0];
//        int    fontSize       = (Integer) fontConfig[1];
//        int    bottomPadding  = (Integer) fontConfig[2];
//
//        // Measure
//        Object[] metrics = prepareImageMetrics(text, fontFilename, fontSize, bottomPadding);
//        Font customFont      = (Font)    metrics[0];
//        int  ascent          = (Integer) metrics[1];
//        int  imageWidth      = (Integer) metrics[2];
//        int  imageHeight     = (Integer) metrics[3];
//        int  textRenderWidth = (Integer) metrics[4];
//        int  fontLineHeight  = (Integer) metrics[5];
//
//        // Create transparent canvas
//        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
//        Graphics2D g2d = image.createGraphics();
//        g2d.setFont(customFont);
//
//        // Draw 1px outline by drawing text in outlineColor at all offsets around the origin
//        g2d.setColor(outlineColor);
//        for (int dx = -1; dx <= 1; dx++) {
//            for (int dy = -1; dy <= 1; dy++) {
//                if (dx == 0 && dy == 0) continue; // skip the center
//                g2d.drawString(text, dx, ascent + dy);
//            }
//        }
//
//        // Draw gradient-filled text on top
//        GradientPaint fgPaint;
//        if (verticalGradient) {
//            fgPaint = new GradientPaint(
//                0, 0,                          fgColorStart,
//                0, fontLineHeight,            fgColorEnd
//            );
//        } else {
//            fgPaint = new GradientPaint(
//                0, 0,                          fgColorStart,
//                textRenderWidth, 0,           fgColorEnd
//            );
//        }
//        g2d.setPaint(fgPaint);
//        g2d.drawString(text, 0, ascent);
//
//        g2d.dispose();
//
//        // Print
//        if (halfWidth) {
//            ImagePrinter.printBufferedImageQuadrant(image);
//        } else {
//            ImagePrinter.printBufferedImage(image);
//        }
//    }
}