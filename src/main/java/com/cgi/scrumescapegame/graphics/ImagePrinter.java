package com.cgi.scrumescapegame.graphics;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImagePrinter {

	private static final String ANSI_RESET = "\u001B[0m";
	private static final String UPPER_HALF_BLOCK = "▀";
	private static final String LOWER_HALF_BLOCK = "▄"; 

	private static final Map<String, String> quadrantMap = new HashMap<>();

	static {
		quadrantMap.put("0010", "▖");
		quadrantMap.put("0001", "▗");
		quadrantMap.put("1000", "▘");
		quadrantMap.put("1011", "▙");
		quadrantMap.put("1001", "▚");
		quadrantMap.put("1110", "▛");
		quadrantMap.put("1101", "▜");
		quadrantMap.put("0100", "▝");
		quadrantMap.put("0110", "▞");
		quadrantMap.put("0111", "▟");
		quadrantMap.put("0000", " ");
		quadrantMap.put("1111", "█");
		quadrantMap.put("1100", "▀");
		quadrantMap.put("0011", "▄");
		quadrantMap.put("1010", "▌");
		quadrantMap.put("0101", "▐");
	}

	public static void printImage(String imagePath) {
		try (InputStream imageStream = ImagePrinter.class.getClassLoader().getResourceAsStream(imagePath)) {
			if (imageStream == null) {
				System.err.println("Error: Image resource not found at classpath location: " + imagePath);
				return;
			}

			BufferedImage image = ImageIO.read(imageStream);
			if (image == null) {
				System.err.println("Error: Could not read image. Unsupported format or corrupt file (from classpath): "
						+ imagePath);
				return;
			}

			if (image.getWidth() == 0 || image.getHeight() == 0) {
				System.err.println("Error: Image has zero width or height: " + imagePath);
				return;
			}

			printBufferedImage(image);
		} catch (IOException e) {
			System.err.println("Error processing image '" + imagePath + "': " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.err.println("Error with image dimensions for '" + imagePath + "': " + e.getMessage());
		}
	}

	public static void printBufferedImage(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();

		if (width == 0 || height == 0 || height < 2) {

			System.err.println("Warning: Image is too small to print (" + width + "x" + height
					+ "). Needs at least 1x2 pixels for this method's logic.");

			if (width > 0 && height == 1) {

				StringBuilder textArt = new StringBuilder();
				for (int x = 0; x < width; x++) {
					Color color = new Color(image.getRGB(x, 0), true);
					if (color.getAlpha() == 0) {
						textArt.append(" ");
					} else {
						textArt.append(String.format("\u001B[38;2;%d;%d;%dm",
								color.getRed(), color.getGreen(), color.getBlue()));
						textArt.append("\u001B[49m"); 
						textArt.append(LOWER_HALF_BLOCK);
						textArt.append(ANSI_RESET);
					}
				}
				System.out.println(textArt.toString());
			}
			return;
		}

		StringBuilder textArt = new StringBuilder();

		for (int y = 0; y < height - 1; y += 2) {
			if (y > 0) { 
				textArt.append("\n");
			}
			for (int x = 0; x < width; x++) {
				Color topColor = new Color(image.getRGB(x, y), true); 
				Color bottomColor = new Color(image.getRGB(x, y + 1), true); 

				boolean topIsTransparent = topColor.getAlpha() == 0;
				boolean bottomIsTransparent = bottomColor.getAlpha() == 0;

				if (topIsTransparent && bottomIsTransparent) {

					textArt.append(" ");
				} else if (!topIsTransparent && bottomIsTransparent) {

					textArt.append(String.format("\u001B[38;2;%d;%d;%dm",
							topColor.getRed(), topColor.getGreen(), topColor.getBlue()));
					textArt.append("\u001B[49m"); 
					textArt.append(UPPER_HALF_BLOCK);
					textArt.append(ANSI_RESET);
				} else if (topIsTransparent && !bottomIsTransparent) {

					textArt.append(String.format("\u001B[38;2;%d;%d;%dm",
							bottomColor.getRed(), bottomColor.getGreen(), bottomColor.getBlue()));
					textArt.append("\u001B[49m"); 
					textArt.append(LOWER_HALF_BLOCK);
					textArt.append(ANSI_RESET);
				} else {

					textArt.append(String.format("\u001B[38;2;%d;%d;%dm",
							topColor.getRed(), topColor.getGreen(), topColor.getBlue()));
					textArt.append(String.format("\u001B[48;2;%d;%d;%dm",
							bottomColor.getRed(), bottomColor.getGreen(), bottomColor.getBlue()));
					textArt.append(UPPER_HALF_BLOCK);
					textArt.append(ANSI_RESET);
				}
			}
		}

		if (height % 2 != 0) {
			if (height > 1) { 
                 textArt.append("\n");
            }
			int y = height - 1; 
			for (int x = 0; x < width; x++) {
				Color color = new Color(image.getRGB(x, y), true);
				if (color.getAlpha() == 0) {
					textArt.append(" ");
				} else {

					textArt.append(String.format("\u001B[38;2;%d;%d;%dm",
							color.getRed(), color.getGreen(), color.getBlue()));
					textArt.append("\u001B[49m"); 
					textArt.append(LOWER_HALF_BLOCK);
					textArt.append(ANSI_RESET);
				}
			}
		}

		System.out.println(textArt.toString());
	}

	public static void printImageQuadrant(String imagePath) {
		try (InputStream imageStream = ImagePrinter.class.getClassLoader().getResourceAsStream(imagePath)) {
			if (imageStream == null) {
				System.err.println("Error: Image resource not found at classpath location: " + imagePath);
				return;
			}

			BufferedImage image = ImageIO.read(imageStream);
			if (image == null) {
				System.err.println("Error: Could not read image. Unsupported format or corrupt file: " + imagePath);
				return;
			}

			printBufferedImageQuadrant(image);

		} catch (IOException e) {
			System.err.println("Error processing image for quadrant printing '" + imagePath + "': " + e.getMessage());
		}
	}

	private static class PixelInfo {
		final Color color;
		final boolean isOn;

		PixelInfo(Color color, boolean isOn) {
			this.color = color;
			this.isOn = isOn;
		}
	}

	private static PixelInfo getPixelInfo(BufferedImage image, int x, int y, int originalWidth, int originalHeight) {
		if (x < 0 || y < 0 || x >= originalWidth || y >= originalHeight) {
			return new PixelInfo(Color.BLACK, false); 
		}

		Color color = new Color(image.getRGB(x, y), true);

		int brightness = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
		boolean isOnState = brightness < 127; 
		return new PixelInfo(color, isOnState);
	}

	private static Color averageColors(List<Color> colors) {
		if (colors == null || colors.isEmpty()) {
			return Color.BLACK; 
		}

		long sumR = 0, sumG = 0, sumB = 0;

		int count = 0;
		for (Color c : colors) {
			if (c != null) { 
				sumR += c.getRed();
				sumG += c.getGreen();
				sumB += c.getBlue();

				count++;
			}
		}
		if (count == 0) return Color.BLACK;

		return new Color((int) (sumR / count), (int) (sumG / count), (int) (sumB / count) );
	}

	public static void printBufferedImageQuadrant(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();

		if (width == 0 || height == 0) {
			System.err.println("Warning: Image is too small to print with quadrants (" + width + "x" + height
					+ "). Needs at least 1x1 pixels.");
			return;
		}

		StringBuilder textArt = new StringBuilder();

		for (int y = 0; y < height; y += 2) {
            if (y > 0) { 
                textArt.append("\n");
            }
			for (int x = 0; x < width; x += 2) {
				PixelInfo p_ul = getPixelInfo(image, x, y, width, height); 
				PixelInfo p_ur = getPixelInfo(image, x + 1, y, width, height); 
				PixelInfo p_ll = getPixelInfo(image, x, y + 1, width, height); 
				PixelInfo p_lr = getPixelInfo(image, x + 1, y + 1, width, height); 

				String key = (p_ul.isOn ? "1" : "0") +
						(p_ur.isOn ? "1" : "0") +
						(p_ll.isOn ? "1" : "0") +
						(p_lr.isOn ? "1" : "0");

				String character;
				Color fgColor, bgColor;

				if (key.equals("0000")) { 

					character = quadrantMap.getOrDefault(key, " "); 
					bgColor = averageColors(List.of(p_ul.color, p_ur.color, p_ll.color, p_lr.color));

					boolean allTransparent = p_ul.color.getAlpha()==0 && p_ur.color.getAlpha()==0 && p_ll.color.getAlpha()==0 && p_lr.color.getAlpha()==0;
					if(allTransparent){
						textArt.append(" ");
						continue;
					}
					fgColor = Color.BLACK; 
                                textArt.append(String.format("\u001B[48;2;%d;%d;%dm", bgColor.getRed(), bgColor.getGreen(),
                                        bgColor.getBlue())); 
                                textArt.append(character);

				} else if (key.equals("1111")) { 

					character = quadrantMap.getOrDefault(key, "█"); 
					fgColor = averageColors(List.of(p_ul.color, p_ur.color, p_ll.color, p_lr.color));

					boolean allTransparent = p_ul.color.getAlpha()==0 && p_ur.color.getAlpha()==0 && p_ll.color.getAlpha()==0 && p_lr.color.getAlpha()==0;
					if(allTransparent){
						textArt.append(" ");
						continue;
					}
                    textArt.append(String.format("\u001B[38;2;%d;%d;%dm", fgColor.getRed(), fgColor.getGreen(),
                            fgColor.getBlue()));
                    textArt.append("\u001B[49m"); 
                    textArt.append(character);

				} else {

					character = quadrantMap.getOrDefault(key, "?"); 

					List<Color> onPixelColors = new ArrayList<>();
					List<Color> offPixelColors = new ArrayList<>();

					if (p_ul.isOn) onPixelColors.add(p_ul.color); else offPixelColors.add(p_ul.color);
					if (p_ur.isOn) onPixelColors.add(p_ur.color); else offPixelColors.add(p_ur.color);
					if (p_ll.isOn) onPixelColors.add(p_ll.color); else offPixelColors.add(p_ll.color);
					if (p_lr.isOn) onPixelColors.add(p_lr.color); else offPixelColors.add(p_lr.color);

					fgColor = averageColors(onPixelColors);
					bgColor = averageColors(offPixelColors);

					boolean fgIsTransparent = onPixelColors.stream().allMatch(c -> c.getAlpha() == 0) && !onPixelColors.isEmpty();
					boolean bgIsTransparent = offPixelColors.stream().allMatch(c -> c.getAlpha() == 0) && !offPixelColors.isEmpty();

					if (fgIsTransparent && bgIsTransparent) { 
						textArt.append(" ");
						continue;
					} else if (fgIsTransparent) { 

						textArt.append(String.format("\u001B[48;2;%d;%d;%dm", bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue()));
						textArt.append(" "); 
					} else if (bgIsTransparent) { 

						textArt.append(String.format("\u001B[38;2;%d;%d;%dm", fgColor.getRed(), fgColor.getGreen(), fgColor.getBlue()));
						textArt.append("\u001B[49m"); 
						textArt.append(character);
					} else { 
						textArt.append(String.format("\u001B[38;2;%d;%d;%dm", fgColor.getRed(), fgColor.getGreen(), fgColor.getBlue()));
						textArt.append(String.format("\u001B[48;2;%d;%d;%dm", bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue()));
						textArt.append(character);
					}
				}
				textArt.append(ANSI_RESET);
			}
		}
		System.out.println(textArt);
	}

	public static Color brighten(Color color, float factor) {
		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();

		float[] hsb = Color.RGBtoHSB(r, g, b, null);

		hsb[2] = Math.min(1.0f, hsb[2] + ( (1.0f - hsb[2]) * factor) );

		Color newColor = Color.getHSBColor(hsb[0], hsb[1], hsb[2]);

		return new Color(newColor.getRed(), newColor.getGreen(), newColor.getBlue());
	}
}