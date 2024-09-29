package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import com.diogonunes.jcolor.*;


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


    public static Attribute colorAttribute(String color) {
        if (color.toLowerCase().equals("cyan")) {
            return Attribute.CYAN_BACK();
        } else if (color.toLowerCase().equals("green")) {
            return Attribute.GREEN_BACK();
        } else if (color.toLowerCase().equals("magenta")) {
            return Attribute.MAGENTA_BACK();
        } else if (color.toLowerCase().equals("red")) {
            return Attribute.RED_BACK();
        } else if (color.toLowerCase().equals("yellow")) {
            return Attribute.YELLOW_BACK();
        } else if (color.toLowerCase().equals("blue")) {
            return Attribute.BLUE_BACK();
        } else if (color.toLowerCase().equals("white")) {
            return Attribute.WHITE_BACK();
        } else if (color.toLowerCase().equals("black")) {
            return Attribute.BLACK_BACK();
        } else {
            return Attribute.WHITE_BACK();
        }
    }
}