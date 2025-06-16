package com.cgi.scrumescapegame.animations;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.cgi.scrumescapegame.Game;
import com.cgi.scrumescapegame.graphics.ImagePrinter;
import com.cgi.scrumescapegame.graphics.PrintMethods;

public class DoorOpeningAnimation {
    private static final BufferedImage frames;
    private static final int width = 94;
    private static final int height = 46;
    private static final int frameCount = 6;
    private static final int frameDelayMs = 83;

    static {
        try {
            frames = ImageIO.read(DoorOpeningAnimation.class.getResource("/animations/door.png"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void playAnimation() {
        PrintMethods.hideCursor();
        for (int i = 0; i < frameCount; i++) {
            BufferedImage door = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = door.createGraphics();
            graphics.drawImage(frames.getSubimage(0, height * i, width, height), 0, 0, null);
            ImagePrinter.printBufferedImage(door);
            PrintMethods.moveCursorUp(height / 2);
            Game.pause(frameDelayMs);
        }
        PrintMethods.moveCursorDown(height / 2);
        PrintMethods.showCursor();
    }
}
