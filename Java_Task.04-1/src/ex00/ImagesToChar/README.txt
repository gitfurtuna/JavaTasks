# Instructions for Compile and Run

1. Open command line and navigate to the root folder of the project (ImagesToChar).
2. Compile the Java files using the following command:
    javac -d ../target src/edu/school21/printer/app/*.java src/edu/school21/printer/logic/*.java
3. Run the application using the following command:
    java -cp ../target edu.school21.printer.app.Program <image_path> <char_white> <char_black>

Where:
- `<image_path>` is the path to the BMP image you want to convert (for example, '../it.bmp').
- `<char_white>` is the character to represent white pixels (for example, '.').
- `<char_black>` is the character to represent black pixels (for example, '0').
