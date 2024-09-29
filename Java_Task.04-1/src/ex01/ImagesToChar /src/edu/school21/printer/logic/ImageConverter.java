package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ImageConverter {
    public static char[][] convertImageToCharArray(InputStream image1, char whiteChar, char blackChar) throws IOException {
        BufferedImage image = ImageIO.read(image1);
        char[][] charArray = new char[image.getHeight()][image.getWidth()];
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = image.getRGB(x, y);
                charArray[y][x] = (pixel == 0xFFFFFFFF) ? whiteChar : blackChar;
            }
        }
        return charArray;
    }
}
