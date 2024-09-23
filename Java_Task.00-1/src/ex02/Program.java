import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter queries numbers :");
        int counter = 0;
        ONE: while (true) {
            try {
                String input = scan.nextLine();
                int number = Integer.parseInt(input);
                if (number <= 1) {
                    System.err.println("Query is not found");
                    System.exit(-1);
                }
                if (number != 42) {
                    int query = sumDigits(number);
                    if (reallyPrimeNumber(query)) {
                        counter++;
                        continue ONE;
                    }

                }
                if (number == 42){
                    System.out.println("Count of coffee-request – " + counter);
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println("You can enter only INT NUMBER, try again, please !");
                System.out.println("Enter queries numbers :");
                continue ONE;
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

    static int sumDigits (int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number = number / 10;
        }
        return sum;
    }
}



