package com.cgi.scrumescapegame.graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;

import com.diogonunes.jcolor.Attribute;

public class SplashScreen {
    static BufferedImage logoImage, spriteSheetImage, backgroundImage;
    static int width, height, columns;
    static float hueValue;
    static Random randomGenerator = new Random();
    static List<Entity> entityList = new ArrayList<>();

    static class Entity {
        float positionX, positionY, velocityX;
        BufferedImage image;
    }

    public static void showSplashScreen() {
        // 1) Load images from classpath
        try {
            logoImage           = ImageIO.read(SplashScreen.class.getResourceAsStream("/logo_splash.png"));
            spriteSheetImage    = ImageIO.read(SplashScreen.class.getResourceAsStream("/entities.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2) Setup sizes
        width               = 92;
        height              = 48;
        backgroundImage     = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        columns             = spriteSheetImage.getWidth() / 18;
        hueValue            = randomGenerator.nextFloat();

        PrintMethods.hideCursor();

        // 5) Main loop
        while(true) {
            // 6) Shift hue and compute corner colors
            float hueStart    = hueValue;
            float hueEnd      = (hueValue + 0.25f) % 1f;
            int startColor    = Color.HSBtoRGB(hueStart, 0.6f, 1f);
            int endColor      = Color.HSBtoRGB(hueEnd,   0.6f, 1f);
            hueValue          = (hueValue + 1f/360f) % 1f;

            // 4) Fill background with topleft→bottomright gradient
            int gradientDenominator = width + height - 2;
            for (int pixelY = 0; pixelY < height; pixelY++) {
                for (int pixelX = 0; pixelX < width; pixelX++) {
                    float t = (pixelX + pixelY) / (float) gradientDenominator;
                    int redComponent   = (int)(((startColor >> 16 & 255) * (1 - t) + (endColor >> 16 & 255) * t));
                    int greenComponent = (int)(((startColor >>  8 & 255) * (1 - t) + (endColor >>  8 & 255) * t));
                    int blueComponent  = (int)(((startColor       & 255) * (1 - t) + (endColor       & 255) * t));
                    backgroundImage.setRGB(pixelX, pixelY, (redComponent << 16) | (greenComponent << 8) | blueComponent);
                }
            }

            // 7) Spawn a new entity from left or right
            if (randomGenerator.nextFloat() < 0.15f) {
                Entity entity = new Entity();
                entity.positionY = randomGenerator.nextInt(height - 18);
                if (randomGenerator.nextBoolean()) {
                    entity.positionX = -18;
                    entity.velocityX =  1 + randomGenerator.nextFloat() * 2;
                } else {
                    entity.positionX =  width;
                    entity.velocityX = -(1 + randomGenerator.nextFloat() * 2);
                }
                int entityIndex = randomGenerator.nextInt(columns * (spriteSheetImage.getHeight() / 18));
                int spriteX     = (entityIndex % columns) * 18;
                int spriteY     = (entityIndex / columns) * 18;
                entity.image    = spriteSheetImage.getSubimage(spriteX, spriteY, 18, 18);
                entityList.add(entity);
            }

            // 8) Update & draw entities, 9) draw logo on top
            Graphics2D graphics = backgroundImage.createGraphics();
            for (Iterator<Entity> iterator = entityList.iterator(); iterator.hasNext();) {
                Entity entity = iterator.next();
                entity.positionX += entity.velocityX;
                if (entity.velocityX > 0
                    ? entity.positionX > width
                    : entity.positionX + 18 < 0)
                {
                    iterator.remove();
                } else {
                    graphics.drawImage(
                        entity.image,
                        (int)(entity.positionX + (entity.velocityX < 0 ? 18 : 0)),
                        (int)entity.positionY,
                        (int)(entity.velocityX < 0 ? -18 : 18),
                        18,
                        null
                    );
                }
            }
            graphics.drawImage(logoImage, 0, 0, null);
            graphics.dispose();

            // The text we want to draw
            String text = "Druk op enter om te starten.";

            // Where in the terminal (row, col) we want the first character
            int termRow       = 20;
            int termColStart  = 33;

            // Which scan‐line in the image corresponds to this terminal row
            int imageTextLine = 38; // (20 * 2) - 2

            // 1) draw background
            PrintMethods.cursorHome();
            ImagePrinter.printBufferedImage(backgroundImage);

            // 2) position cursor at the start of the text
            PrintMethods.setCursorPosition(termRow, termColStart);

            // 3) for each character: sample its background pixel and print it
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);

                // convert terminal column → image X coordinate
                int sampleX = (termColStart + i);
                int sampleY = imageTextLine;

                // get RGB components
                int rgb = backgroundImage.getRGB(sampleX, sampleY);
                int r   = (rgb >> 16) & 0xFF;
                int g   = (rgb >>  8) & 0xFF;
                int b   =  rgb        & 0xFF;

                // compute rec. 601 luma:
                int lum = (int)((0.299*r + 0.587*g + 0.114*b));

                // pick white if dark background, otherwise black
                Attribute fg = (lum < 128
                                ? Attribute.TEXT_COLOR(255,255,255)
                                : Attribute.TEXT_COLOR(0,0,0));

                // print one character with its sampled background color
                PrintMethods.printColor(String.valueOf(c), new Attribute[]{fg, Attribute.BACK_COLOR(r, g, b), Attribute.BOLD()});
            }

            try {
                Thread.sleep(41);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                if (System.in.available() > 0 && System.in.read() == '\n') {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        PrintMethods.showCursor();
    }
}
