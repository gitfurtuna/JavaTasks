package edu.school21.printer.app;

import java.io.IOException;

public class Program {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Instruction: java Program <image_path> <white_char> <black_char>");
            return;
        }
        String imagePath = args[0];
        char whiteChar = args[1].charAt(0);
        char blackChar = args[2].charAt(0);
        try {
            char[][] charArray = ImageConverter.convertImageToCharArray(imagePath, whiteChar, blackChar);
            for (char[] row : charArray) {
                for (char c : row) {
                    System.out.print(c);
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Error reading image file: " + e.getMessage());
        }
    }
}

