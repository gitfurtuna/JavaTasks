package edu.school21.printer.app;

import java.io.InputStream;
import java.io.IOException;

public class Program {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Instruction: java Program <white_char> <black_char>");
            return;
        }

        char whiteChar = args[0].charAt(0);
        char blackChar = args[1].charAt(0);
        try {
            InputStream inputStream = Program.class.getClassLoader().getResourceAsStream("resources/it.bmp");
            if (inputStream == null) {
                throw new IllegalArgumentException("Resource not found: it.bmp");
            }
            char[][] charArray = ImageConverter.convertImageToCharArray(inputStream, whiteChar, blackChar);
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

