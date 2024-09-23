import java.util.Arrays;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the string :");
        System.out.println();
        String input = scan.nextLine();
        System.out.println();
        scan.close();

        int[] frequency = new int[65536];
        char[] characters = new char[65536];
        int charactersIndex = 0;

        for (char c : input.toCharArray()) {
            frequency[c]++;
            if (frequency[c] > 999) frequency[c] = 999;
        }

        for (int i = 0; i < frequency.length; i++) {
            if (frequency[i] > 0) {
                characters[charactersIndex++] = (char) i;
            }

        }


        Character[] charactersObjects = new Character[charactersIndex];
        for (int i = 0; i < charactersIndex; i++) {
            charactersObjects[i] = characters[i];
        }

        Arrays.sort(charactersObjects, 0, charactersIndex, (a, b) -> {
            if (frequency[(int) a] == frequency[(int) b]) {
                return Character.compare((char) a, (char) b);
            }
            return Integer.compare(frequency[(int) b], frequency[(int) a]);
        });


        for (int i = 0; i < charactersIndex; i++) {
            characters[i] = charactersObjects[i];
        }


        int maxFrequency = Arrays.stream(frequency).max().orElseThrow();
        int top = Math.min(10, charactersIndex);
        float oneSymbol = maxFrequency / 10.0f;

        for (int heightGraph = 10; heightGraph >= 0; heightGraph--) {
            for (int i = 0; i < top; i++) {
                char c = characters[i];
                int freq = frequency[c];
                int countSymbol = (int) (freq / oneSymbol);
                if (countSymbol == heightGraph) {
                    if ((float) (freq / 10) >= 1) {
                        System.out.print(freq + "  ");
                    } else if ((float) (freq / 10) < 1) {
                        System.out.print(" " + freq + "  ");
                    }
                } else if (countSymbol > heightGraph) {
                    System.out.print(" #  ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();

        }


        for (int i = 0; i < top; i++) {
            System.out.print(" " + characters[i] + "  ");
        }
    }
}
