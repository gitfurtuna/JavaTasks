import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Enter an integer :");
            String input = scan.nextLine();
            try {
                int number = Integer.parseInt(input);
                if (number <= 1) {
                    System.err.println("IllegalArgument ");
                    System.exit(-1);
                } else if (reallyPrimeNumber(number)) {
                    int count = counterForIteration(number);
                    System.out.println("true " + count);
                } else {
                    int count = counterForIteration(number);
                    System.out.println("false " + count);
                }
            } catch (NumberFormatException e) {
                System.out.println("You can enter only INT NUMBER, try again, please !");

            }
        }
    }

    static boolean reallyPrimeNumber(int number) {
        boolean result = true;
        for (int i = 2; i <= Math.round(Math.sqrt(number)); i++) {
            if (number % i == 0) {
                result = false;
                break;
            }
        }
        return result;
    }

    static int counterForIteration(int number) {
        int counter = 0;
        for (int i = 2; i <= Math.round(Math.sqrt(number)); i++) {
            counter++;
            if (number % i == 0) {
                break;
            }

        }
        return counter;
    }
}



