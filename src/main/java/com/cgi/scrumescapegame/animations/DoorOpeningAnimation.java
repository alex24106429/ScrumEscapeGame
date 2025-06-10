package com.cgi.scrumescapegame.animations;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.cgi.scrumescapegame.graphics.ImagePrinter;
import com.cgi.scrumescapegame.graphics.PrintMethods;

public class DoorOpeningAnimation {
    static final int WIDTH  = 92;
    static final int HEIGHT = 48;

    // brick tile dimensions
    static final int TILE_W    = 8;  // total tile width (brick + mortar)
    static final int TILE_H    = 4;  // total tile height (brick + mortar)
    static final int BRICK_W   = 7;  // brick body width
    static final int BRICK_H   = 3;  // brick body height

    // door dimensions when closed
    static final int DOOR_W    = 40;
    static final int DOOR_H    = 42;
    static final int DOOR_Y    = (HEIGHT - DOOR_H) / 2;
    static final int HINGE_X   = (WIDTH  - DOOR_W) / 2;

    // little handle on the free edge
    static final int HANDLE_OFFSET  = 3;
    static final int HANDLE_RADIUS  = 1;
    static final int HANDLE_Y       = DOOR_Y + DOOR_H/2;

    static BufferedImage back =
        new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

    public static void playAnimation() {
        PrintMethods.hideCursor();

        Color mortarColor = new Color(200,200,200);
        Color brickColor  = new Color(178,34,34);
        Color doorColor   = new Color(101,67,33);
        Color handleColor = Color.YELLOW;

        int totalFrames = 60;
        for (int frame = 0; frame <= totalFrames; frame++) {
            float t     = frame / (float) totalFrames;
            float angle = t * (float)(Math.PI/2);      // 0 → 90°
            int projW   = (int)(DOOR_W * Math.cos(angle));

            Graphics2D g = back.createGraphics();

            // 1) fill entire with mortar
            g.setColor(mortarColor);
            g.fillRect(0,0,WIDTH,HEIGHT);

            // 2) draw brick bodies on top, staggered every other row
            for (int row = 0, y = 0; y < HEIGHT; row++, y += TILE_H) {
                int offsetX = (row % 2 == 1 ? TILE_W/2 : 0);
                for (int x = -offsetX; x < WIDTH; x += TILE_W) {
                    int bx = x + offsetX,
                        by = y;
                    if (bx + BRICK_W > 0 && by + BRICK_H > 0) {
                        g.setColor(brickColor);
                        int w = Math.min(BRICK_W, WIDTH - bx);
                        int h = Math.min(BRICK_H, HEIGHT - by);
                        g.fillRect(bx, by, w, h);
                    }
                }
            }

            // 3) black interior “behind” the opening
            int portalX = HINGE_X + projW;
            int portalW = DOOR_W - projW;
            if (portalW > 0) {
                g.setColor(Color.BLACK);
                g.fillRect(portalX, DOOR_Y, portalW, DOOR_H);
            }

            // 4) draw the door panel shrinking about its left hinge
            g.setColor(doorColor);
            if (projW > 0) {
                g.fillRect(HINGE_X, DOOR_Y, projW, DOOR_H);
                // 5) tiny handle on the free edge
                if (projW > HANDLE_OFFSET + HANDLE_RADIUS*2) {
                    int hx = HINGE_X + projW - HANDLE_OFFSET - HANDLE_RADIUS;
                    int hy = HANDLE_Y - HANDLE_RADIUS;
                    g.setColor(handleColor);
                    g.fillOval(hx, hy, HANDLE_RADIUS*2, HANDLE_RADIUS*2);
                }
            }

            g.dispose();

            // 6) blit to terminal
            PrintMethods.cursorHome();
            ImagePrinter.printBufferedImage(back);

            // 7) frame delay
            try { Thread.sleep(40); }
            catch (InterruptedException e) { /* ignore */ }
        }

        PrintMethods.showCursor();
    }
}
