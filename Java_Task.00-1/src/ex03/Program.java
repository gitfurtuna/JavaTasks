import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter \"Week\" -> space_key -> \"number of week\" from 1 to 18 -> enter_key -> enter 5 \"grades for tests\" from 1 to 9 separated by spaces\nto see a graph that shows the change in student progress over several weeks.");
        System.out.println("42 is the limit of input data.");
        System.out.println();
        int counter = 0;
        long totalMinGrade = 0;
        while (counter <= 18) {
                counter++;
                String input = scan.nextLine();
                if (input.matches("^42$") || input.matches("^42\\s$")) {
                    break;
                }
                if (input.matches("^Week ([1-9]|1[0-8])$") || input.matches("^Week ([1-9]|1[0-8])\\s$")) {
                    String input2 = scan.nextLine();
                    int weekNum = Integer.parseInt(input.replaceAll("Week ","").trim());
                    long cntSpace = input2.codePoints().filter(Character::isSpaceChar).count();
                    long cntDigit = input2.codePoints().filter(Character::isDigit).count();
                    if ((input2.matches("^(\\d{1}\\s){4}\\d{1}$") || input2.matches("^(\\d{1}\\s){5}$"))  && input2.contains("0")) {
                        System.err.println("Each test can be scored between 1 and 9");
                        System.exit(-1);
                    } else if ((input2.matches("^(\\d{1}\\s){4}\\d{1}$") || input2.matches("^(\\d{1}\\s){5}$")) && !input2.contains("0")) {
                        input2 = input2.replaceAll("\\s", "");
                        int grade = Integer.parseInt(input2);
                        int minGrade = minGrade(grade);
                        totalMinGrade += zeroUP(weekNum) * minGrade;
                    } else if (input2.matches(".*\\b\\d{2,}\\b.*") && input2.matches("^[0-9\\s]+$") && (cntSpace == 4 || cntSpace == 5)) {
                        System.err.println("Each test can be scored between 1 and 9");
                        System.exit(-1);
                    } else if ((input2.matches(".*\\b\\d{2,}\\b.*") && input2.matches("^[0-9\\s]+$") && (cntSpace != 4 || cntSpace != 5)) || (input2.matches("^[0-9\\s]+$") && cntDigit != 5)) {
                        System.err.println("The exact guaranteed number of tests in a week is 5");
                        System.exit(-1);
                    } else {
                        System.err.println("IllegalArgument");
                        System.exit(-1);
                    }
                } else {
                    System.err.println("IllegalArgument");
                    System.exit(-1);
                }
        }
        printReport(totalMinGrade);
    }


    static int minGrade(int grade) {
        int minGrade = grade % 10;
        while (grade > 0) {
            int digit = grade % 10;
            if (minGrade > digit) {
                minGrade = digit;
            }
            grade = grade / 10;
        }
        return minGrade;
    }


    private static long zeroUP (int weekNum) {
        long zeroUp = 1;
        for (int i = weekNum ; i != 1; i--) {
            zeroUp *= 10;
        }
        return zeroUp;
    }

    static void printReport(long totalMinGrade) {
        for (int i = 1; i <= 18; i++) {
            long minGrade = totalMinGrade % 10;
            int intMinGrade = Math.toIntExact(minGrade);
            totalMinGrade /= 10;
            if (minGrade == 0) continue;
            System.out.println("Week " + i + " " + "=".repeat(intMinGrade) +">");
        }
    }
}



