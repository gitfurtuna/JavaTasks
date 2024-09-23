import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

        public class Program {
            private static Path currentDirectory;

            public Program (Path startingPath) {
                this.currentDirectory = startingPath;
            }

            public static long getDirectorySize(Path dir) {
                long size = 0;
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
                    for (Path entry : stream) {
                        if (Files.isDirectory(entry)) {
                            size += getDirectorySize(entry);
                        } else {
                            size += Files.size(entry);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error calculating directory size: " + e.getMessage());
                }
                return size;
            }

            public static void ls() {
                try {
                    DirectoryStream<Path> stream = Files.newDirectoryStream(currentDirectory);
                    for (Path entry : stream) {
                        BasicFileAttributes attrs = Files.readAttributes(entry, BasicFileAttributes.class);
                        long sizeInKB = 0;
                        if (Files.isDirectory(entry)) {
                            sizeInKB = getDirectorySize(entry) / 1024;
                        } else {
                            sizeInKB = attrs.size() / 1024;
                        }
                        System.out.println(entry.getFileName() + " " + sizeInKB + " KB ");
                    }
                }catch (IOException e) {
                        System.out.println("Error reading directory: " + e.getMessage());
                    }
                }

            public static void cd(String folderName) {
                Path newPath = currentDirectory.resolve(folderName);
                if (Files.isDirectory(newPath)) {
                    currentDirectory = newPath.normalize();
                    System.out.println(currentDirectory);
                } else {
                    System.out.println("No such directory");
                }
            }

            public static void mv(String source, String destination) {
                Path sourcePath = currentDirectory.resolve(source);
                Path destinationPath;

                if (destination.startsWith("/")) {
                    destinationPath = Path.of(destination).normalize();
                } else {
                    destinationPath = currentDirectory.resolve(destination);
                }

                if (Files.isDirectory(destinationPath)) {
                    destinationPath = destinationPath.resolve(sourcePath.getFileName());
                }

                try {
                    Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    System.out.println("Error moving/renaming file: " + e.getMessage());
                }
            }


            public static void main(String[] args) {
                if (args.length != 1 || !args[0].startsWith("--current-folder=")) {
                    System.out.println("Instruction: java Program --current-folder=PATH_TO_DIRECTORY");
                    return;
                }
                String[] parts = args[0].split("=");
                Path startPath = Path.of(parts[1]);
                if (!Files.isDirectory(startPath)) {
                    System.out.println("Provided path is not a directory.");
                    return;
                }

                    Program utility = new Program(startPath);
                    System.out.println(currentDirectory);

                    Scanner scanner = new Scanner(System.in);
                    String cmd = "";
                   ONE: while (true) {
                        System.out.print("-> ");
                        cmd = scanner.nextLine().trim();
                        String[] elem = cmd.split(" ");
                        String command = elem[0];
                        switch (command) {
                            case "ls":
                                utility.ls();
                                break;
                            case "cd":
                                if (elem.length >= 2) {
                                    utility.cd(elem[1]);
                                } else {
                                    System.out.println("Instruction: cd <folder_name>");
                                }
                                break;
                            case "mv":
                                if (elem.length >= 3) {
                                    String source = elem[1];
                                    String destination = elem[2];
                                    utility.mv(source, destination);
                                } else {
                                    System.out.println("Instruction: mv <source> <destination>");
                                }
                                break;
                            case "exit":
                                break ONE;
                            default:
                                System.out.println("Unknown command: " + cmd);
                                break;
                        }

                    }
            }

        }
