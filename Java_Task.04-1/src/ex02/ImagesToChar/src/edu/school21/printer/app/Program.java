package edu.school21.printer.app;

import java.io.InputStream;
import java.io.IOException;
import com.beust.jcommander.*;
import edu.school21.printer.logic.ImageConverter;
import com.diogonunes.jcolor.*;
import java.lang.reflect.Method;

@Parameters(separators = "=")
public class Program {

    @Parameter(names = "--white", description = "Character for white pixels", required = true)
    private String whiteColor;

    @Parameter(names = "--black", description = "Character for black pixels", required = true)
    private String blackColor;

    public static void main(String[] args) {
        Program program = new Program();
        JCommander jc = new JCommander(program);
        try {
            jc.parse(args);
        } catch (Exception e) {
            jc.usage();
            return;
        }

        Attribute whiteColorC = ImageConverter.colorAttribute(program.whiteColor);
        Attribute blackColorC = ImageConverter.colorAttribute(program.blackColor);

        try {
            InputStream inputStream = Program.class.getClassLoader().getResourceAsStream("resources/it.bmp");
            if (inputStream == null) {
                throw new IllegalArgumentException("Resource not found: it.bmp");
            }
            char[][] charArray = ImageConverter.convertImageToCharArray(inputStream, 'W' , 'B');
            for (char[] row : charArray) {
                for (char c : row) {
                    if (c == 'W') {
                        System.out.print(Ansi.colorize(" ", whiteColorC));
                    } else {
                        System.out.print(Ansi.colorize(" ", blackColorC));
                    }

                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Error reading image file: " + e.getMessage());
        }
    }
}

