import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

// For test enter src/ex00/forTest/program.png
// For test enter src/ex00/forTest/test.txt


public class Program {
    private static Map<String, byte[]> loadSignatures(String filePath) {
        Map<String, byte[]> signaturesMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 2) {
                    String type = parts[0];
                    String[] hexValues = parts[1].trim().split(" ");
                    byte[] bytes = new byte[hexValues.length];
                    for (int i = 0; i < hexValues.length; i++) {
                        bytes[i] = (byte) Integer.parseInt(hexValues[i], 16);
                    }
                    signaturesMap.put(type, bytes);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return signaturesMap;
    }

    private static void checkFileType(String filePath, Map<String, byte[]> signaturesMap, List<String> results) {
        try {
            Path fileToCheck = Path.of(filePath);
            byte[] fileBytesToCheck = Files.readAllBytes(fileToCheck);
            boolean isTypeFound = false;
            for (Map.Entry<String, byte[]> entry : signaturesMap.entrySet()) {
                byte[] signature = entry.getValue();
                if (fileBytesToCheck.length >= signature.length) {
                    if (Arrays.equals(Arrays.copyOfRange(fileBytesToCheck, 0, signature.length), signature)) {
                        String keyType = entry.getKey();
                        results.add(keyType);
                        System.out.println("PROCESSED");
                        isTypeFound = true;
                        break;
                    }
                }
            }
            if (!isTypeFound) {
                System.out.println("UNDEFINED");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveFileType(String filePath, List<String> results) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            String content = String.join("\n", results);
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {


        String signaturesPath = "src/ex00/signatures.txt";
        String saveFileType = "src/ex00/result.txt";
        Map<String, byte[]> signaturesMap = loadSignatures(signaturesPath);
        List<String> results = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

            while (true) {
            System.out.print("-> ");
            String filePath = scanner.nextLine().trim();
            if (filePath.equals("42")) {
                if (results.isEmpty()){
                    System.out.println("Results to be written: []\n" + "No results to write.");
                } else {
                    System.out.println("Results to be written:" + results.toString());
                }
                break;
            } else if (filePath.isEmpty()) {
                System.out.println("Enter file path");
            } else {
                File file = new File(filePath);
                if (!file.exists()) {
                    System.out.println("File does not exist");
                } else {
                    checkFileType(filePath, signaturesMap, results);
                    saveFileType(saveFileType, results);

                }

            }

        }
    }
}
