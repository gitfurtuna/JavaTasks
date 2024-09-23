import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

import static java.lang.Math.sqrt;

public class Program {
    private static String readFilesFindSimilarity (String filename0, String filename1, Set<String> wordsSet) {
        Vector<Integer> frequency0 = null;
        Vector<Integer> frequency1 = null;
        try {
            BufferedReader reader0 = new BufferedReader(new FileReader(filename0));
            BufferedReader reader1 = new BufferedReader(new FileReader(filename1));
            String line0 = null;
            String line1 = null;
            String[] symbols0 = new String[0];
            String[] symbols1 = new String[0];
            while ((line0 = reader0.readLine()) != null || (line1 = reader1.readLine()) != null) {
                if (line0 != null) {
                    symbols0 = line0.split(" ");
                    for (String el : symbols0) {
                        wordsSet.add(el);
                    }
                }
                if (line1 != null) {
                    symbols1 = line1.split(" ");
                    for (String el : symbols1) {
                        wordsSet.add(el);
                    }
                }
            }
            frequency0 = frequency(symbols0, wordsSet);
            frequency1 = frequency(symbols1, wordsSet);

            saveFile("src/ex01/dictionary.txt", wordsSet);

            reader0.close();
            reader1.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.DOWN);

        return df.format(similarity(frequency0, frequency1));
    }


    private static void saveFile(String filePath, Set<String> results) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            String content = String.join(", ", results);
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static Vector<Integer> frequency (String [] example, Set<String> results) {
        Map<String,Integer> frequency = new LinkedHashMap<>();
        for (String el : results) {
            frequency.put(el, 0);
        }
        for (String element : example) {
            if (frequency.containsKey(element)) {
                frequency.put(element, frequency.get(element) + 1);
            }
        }
        Vector<Integer> frequencyVector = new Vector<>(frequency.values());
        return frequencyVector;
    }


    private static double similarity (Vector<Integer> first,Vector<Integer> second ) {
        int numerator = 0;
        int num0 = 0;
        int num1 = 0;
        for (int i = 0; i < first.size(); i++) {
            numerator += first.get(i) * second.get(i);
            num0 += first.get(i) * first.get(i);
            num1 += second.get(i) * second.get(i);
        }

        double denominator = sqrt(num0) * sqrt(num1);

        return numerator/ denominator;

    }


    public static void main (String[]args) throws IOException {
                if (args.length != 2) {
                    System.out.println("Instruction: java Program <inputA.txt> <inputB.txt>");
                    return;
                }

                String inputA = args[0];
                String inputB = args[1];

                Set<String> wordsSet = new TreeSet<>();

                System.out.println("Similarity = " + readFilesFindSimilarity(inputA, inputB, wordsSet));


            }
        }

