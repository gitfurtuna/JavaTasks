# Instructions for build and run the JAR archive:

1. Open a terminal in the project root directory (ImagesToChar).
2. Compile the program using the following command:
   `javac -d ./target src/edu/school21/printer/app/*.java src/edu/school21/printer/logic/*.java`
3. Copy image from src to target using the following command::
   `mkdir -p target/resources && cp src/resources/it.bmp target/resources/`  
4. Create the JAR archive using the following command:
   `jar cvfm target/images-to-chars-printer.jar src/manifest.txt -C target/ .`
5. Run the JAR archive using the following command:
   `java -jar target/images-to-chars-printer.jar <char_white> <char_black>`

Where:
- `<char_white>` is the character to represent white pixels (for example, '.').
- `<char_black>` is the character to represent black pixels (for example, '0').
