import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a six-digit int number :");
            String input = scan.nextLine();
            try {
                int number = Integer.parseInt(input);
                number = Math.abs(number);
                if (strictlySixDigits(number)) {
                    int sum = sumDigits(number);
                    System.out.println("The sum of the digits of a six-digit number is " + sum);
                    break;
                } else {
                    System.out.println("You can enter only SIX-digit number, try again, please !");
                }
            } catch (NumberFormatException e) {
                System.out.println("You can enter only INT NUMBER, try again, please !");
            }
        }
        scan.close();
    }

        static int sumDigits (int number) {
            int sum = 0;
            while (number > 0) {
                sum += number % 10;
                number = number / 10;
            }
            return sum;
        }

        static boolean strictlySixDigits ( int number) {
            return String.valueOf(number).length() == 6;
        }
    }

