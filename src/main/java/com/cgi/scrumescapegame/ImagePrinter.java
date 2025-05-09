package com.cgi.scrumescapegame;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePrinter {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String UPPER_HALF_BLOCK = "▀";

    private static final int DEFAULT_OUTPUT_WIDTH_CHARS = 80;

    public static void printImage(String imagePath, int... optionalOutputWidthChars) {
        File imageFile = new File(imagePath);

        if (!imageFile.exists()) {
            System.err.println("Error: Image file not found at " + imagePath);
            return;
        }

        int targetWidthChars = DEFAULT_OUTPUT_WIDTH_CHARS;
        if (optionalOutputWidthChars != null && optionalOutputWidthChars.length > 0 && optionalOutputWidthChars[0] > 0) {
            targetWidthChars = optionalOutputWidthChars[0];
        } else if (optionalOutputWidthChars != null && optionalOutputWidthChars.length > 0) {
            System.err.println("Warning: Specified output width (" + optionalOutputWidthChars[0] + ") is invalid. Using default width: " + DEFAULT_OUTPUT_WIDTH_CHARS);
        }

        try {
            BufferedImage originalImage = ImageIO.read(imageFile);
            if (originalImage == null) {
                System.err.println("Error: Could not read image. Unsupported format or corrupt file: " + imagePath);
                return;
            }

            if (originalImage.getWidth() == 0 || originalImage.getHeight() == 0) {
                System.err.println("Error: Image has zero width or height: " + imagePath);
                return;
            }

            BufferedImage scaledImage = scaleImage(originalImage, targetWidthChars);
            convertToTextAndPrint(scaledImage);
        } catch (IOException e) {
            System.err.println("Error processing image '" + imagePath + "': " + e.getMessage());

        } catch (IllegalArgumentException e) {
            System.err.println("Error with image dimensions for '" + imagePath + "': " + e.getMessage());
        }
    }

    private static BufferedImage scaleImage(BufferedImage originalImage, int targetWidth) {
        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();

        if (originalWidth == 0 || originalHeight == 0) {
            throw new IllegalArgumentException("Image has zero width or height.");
        }

        int targetPixelHeight = (int) (originalHeight * ((double) targetWidth / originalWidth));

        if (targetPixelHeight % 2 != 0) {
            targetPixelHeight--;
        }

        if (targetPixelHeight <= 0) {
            targetPixelHeight = 2;
        }

        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetPixelHeight, Image.SCALE_SMOOTH);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetPixelHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(resultingImage, 0, 0, null);
        g2d.dispose();

        return outputImage;
    }

    private static void convertToTextAndPrint(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        StringBuilder textArt = new StringBuilder();

        for (int y = 0; y < height - 1; y += 2) {
            for (int x = 0; x < width; x++) {
                Color topColor = new Color(image.getRGB(x, y));
                Color bottomColor = new Color(image.getRGB(x, y + 1));

                textArt.append(String.format("\u001B[38;2;%d;%d;%dm",
                        topColor.getRed(), topColor.getGreen(), topColor.getBlue()));

                textArt.append(String.format("\u001B[48;2;%d;%d;%dm",
                        bottomColor.getRed(), bottomColor.getGreen(), bottomColor.getBlue()));

                textArt.append(UPPER_HALF_BLOCK);
                textArt.append(ANSI_RESET);
            }
            if (y < height - 2) {
                textArt.append("\n");
            }
        }
        System.out.println(textArt);
    }
}