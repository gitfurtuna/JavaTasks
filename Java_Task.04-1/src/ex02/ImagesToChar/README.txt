# Instructions for build and run the JAR archive(using external libraries: JCommander for the command line,JCDP or JColor for colored output):

1. Open a terminal in the project root directory (ImagesToChar).
2. To run this project, you need to have the following libraries included in the `lib` folder:
- JCommander (jcommander-*.jar)
- JCDP or JColor (JCDP-*.jar or JColor-*.jar)
3. Compile the Java files:
   `javac -cp "lib/*" src/edu/school21/printer/app/Program.java src/edu/school21/printer/logic/ImageConverter.java -d target`
4. Copy image from src to target using the following command:
   `mkdir -p target/resources && cp src/resources/it.bmp target/resources/`
5.  Extract class files from lib to target using following command:
   `jar xf lib/JColor-5.5.1.jar com`
   `jar xf lib/jcommander-1.82.jar com && mv com target/com`
6. Create the JAR archive using the following command:
   `jar cvfm target/images-to-chars-printer.jar src/manifest.txt -C target/ .`
7. Run the program:
   `java -jar target/images-to-chars-printer.jar --white=<color> --black=<color>`

# Example
Run the program with:
`java -jar target/images-to-chars-printer.jar --white=RED --black=GREEN`





