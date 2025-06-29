package com.cgi.scrumescapegame;

import java.io.IOException;

import com.cgi.scrumescapegame.graphics.ImagePrinter;
import com.cgi.scrumescapegame.graphics.PrintMethods;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class QuickTimeEvent {
	public static float runQuickTimeEvent() {
		final int barWidth = 50;
		final int barHeight = 4;
		final int centerX = barWidth / 2;
		final long frameDelay = 16; // ~60 FPS

		// 1) build the red→green→red background
		BufferedImage barBg = new BufferedImage(barWidth, barHeight, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < barWidth; x++) {
			float t = (x <= centerX)
					? (float) x / centerX
					: (float) (barWidth - 1 - x) / centerX;
			int red;
			int green;
			if (t < 0.5) {
				red = 255;
				green = (int) ((t * 2) * 255);
			} else {
				red = (int) ((1.0 - (t - 0.5) * 2) * 255);
				green = 255;
			}

			int rgb = (red << 16) | (green << 8);
			for (int y = 0; y < barHeight; y++) {
				barBg.setRGB(x, y, rgb);
			}
		}

		PrintMethods.hideCursor();
		// clear any pending input so we don't immediately catch an old keypress
		try {
			while (System.in.available() > 0)
				System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int cursorX = 0;
		int dir = 1; // +1 = moving right, –1 = moving left

		System.out.println("Druk op enter wanneer de lijn in het midden is!");

		// 2) bounce the white line back-and-forth until enter
		while (true) {
			// compose frame
			BufferedImage frame = new BufferedImage(barWidth, barHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = frame.createGraphics();
			g.drawImage(barBg, 0, 0, null);
			g.setColor(Color.WHITE);
			g.drawLine(cursorX, 0, cursorX, barHeight - 1);
			g.dispose();

			// draw at top of terminal
			ImagePrinter.printBufferedImage(frame);

			// move cursor up
			PrintMethods.moveCursorUp(2);

			// check for enter
			try {
				if (System.in.available() > 0 && System.in.read() == '\n') {
					// 1. Calculate the normalized distance from the center (0.0 at center, 1.0 at edge)
					float dist = Math.abs(cursorX - centerX);
					float normalizedDist = dist / centerX;

					// 2. Calculate linear closeness (1.0 at center, 0.0 at edge)
					float linearCloseness = 1f - normalizedDist;

					// 3. Square the linear closeness to make it non-linear and stricter.
					float closeness = linearCloseness * linearCloseness;

					// 4. Clamp the value to ensure it's never negative (good practice)
					closeness = Math.max(0f, closeness);
					PrintMethods.moveCursorDown(2);
					PrintMethods.showCursor();
					return closeness;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			// advance cursor and bounce at edges
			cursorX += dir;
			if (cursorX <= 0) {
				cursorX = 0;
				dir = 1;
			} else if (cursorX >= barWidth - 1) {
				cursorX = barWidth - 1;
				dir = -1;
			}

			// frame delay
			Game.pause(frameDelay);
		}
	}
}
