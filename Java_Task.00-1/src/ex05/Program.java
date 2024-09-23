
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int maxStudents = 10;
        int maxClasses = 10;
        int september2020 = 30;
        int attendanceIndex = 0;
        boolean stop = false;
        String[] Week = new String[]{"TU", "WE", "TH", "FR", "SA", "SU", "MO"};
        String[] Month = new String[september2020 + 1];
        String[][] matrixTable = new String[10][31];


        int d = 0;
        Month[0] = " ".repeat(10);
        for (int i = 1; i < Month.length; i++) {
            Month[i] = Week[d] + " " + (i) + "|";
            d++;
            if (d == 7) {
                d = 0;
            }
        }


        for (int k = 0; k < matrixTable.length; k++) {
            for (int j = 0; j < matrixTable[k].length; j++) {
                if (j / 10 == 0) {
                    matrixTable[k][j] = " ".repeat(9) + "|";
                } else if (j / 10 > 0) {
                    matrixTable[k][j] = " ".repeat(10) + "|";
                }
            }
        }


        int k = 0;
        ONE:
        for (int i = 0; i < maxStudents; i++) {
            String name = scan.nextLine();
            if (name.matches("^[a-zA-Z]{1,10}$")) {
                matrixTable[k][0] = name + " ".repeat(10 - name.length());
                k++;
                continue ONE;
            } else if (name.matches("^[a-zA-Z]{11,}$")) {
                System.err.println("Maximum length of student name is 10 (no spaces)");
                System.exit(-1);
            } else if (name.matches(".")) {
                break ONE;
            } else {
                System.err.println("Incorrect input");
                System.exit(-1);
            }
        }


        TWO:
        for (int j = 0; j < maxClasses; j++) {
            String timetable = scan.nextLine();
            if (timetable.matches("^[1-6]\s(MO|TU|WE|TH|FR|SA|SU)$")) {
                int spaceIndex = timetable.indexOf(' ');
                String time = timetable.substring(0, spaceIndex);
                String day = timetable.substring(spaceIndex + 1);
                for (int i = 0; i < Month.length; i++) {
                    if (Month[i].contains(day)) {
                        Month[i] = time + ":00" + " " + Month[i];
                    }
                }
                continue TWO;
            } else if (timetable.matches(".")) {
                break TWO;
            } else {
                System.err.println("Incorrect input");
                System.exit(-1);
            }
        }


        THREE:
        while (!stop) {
            String attendance = scan.nextLine();
            if (attendance.matches("^[a-zA-Z]{1,10}\s([1-6])\s([1-9]|[12][0-9]|30)\s(NOT_HERE|HERE)$")) {
                String[] parts = attendance.split(" ");
                String name = parts[0];
                String time = parts[1];
                String day = parts[2];
                String status = parts[3];
                int counter = 0;
                for (int z = 0; z < matrixTable.length; z++) {
                    for (int j = 0; j < matrixTable[z].length; j++) {
                        if (matrixTable[z][j].contains(name)) {
                            counter++;
                        }
                    }

                }
                if (counter == 0) {
                    System.err.println("Incorrect name");
                    System.exit(-1);
                }

                for (int i = 1; i < Month.length; i++) {
                    if (Month[i].contains("00")) {
                        String[] elements = Month[i].split(" ");
                        String firstEl = elements[0];
                        String timeEl = firstEl.split(":")[0];
                        String dayEl = elements[2].split("\\|")[0];
                        if (time.equals(timeEl) && day.equals(dayEl)) {
                            for (int m = 0; m < matrixTable.length; m++) {
                                for (int l = 0; l < matrixTable[m].length; l++) {
                                    if (matrixTable[m][l].trim().equals(name)) {
                                        if (status.equals("NOT_HERE")) {
                                            if (Integer.parseInt(dayEl) < 10) {
                                                matrixTable[m][i] = " ".repeat(7) + "-1" + "|";
                                            } else {
                                                matrixTable[m][i] = " ".repeat(8) + "-1" + "|";
                                            }
                                        } else if (status.equals("HERE")) {
                                            if (Integer.parseInt(dayEl) < 10) {
                                                matrixTable[m][i] = " ".repeat(8) + "1" + "|";
                                            } else {
                                                matrixTable[m][i] = " ".repeat(9) + "1" + "|";
                                            }
                                        }
                                        continue THREE;
                                    }
                                }
                            }

                        }

                    }
                }

            } else if (attendance.matches(".")) {
                for (String el : Month) {
                    if (el.contains("00") || el.contains(" ".repeat(10)))
                        System.out.print(el);
                }

                System.out.println();

                for (int i = 0; i < k; i++) {
                    for (int j = 0; j < Month.length; j++) {
                        if (Month[j].contains("00") || Month[j].equals(" ".repeat(10))) {
                            System.out.print(matrixTable[i][j]);
                        }
                    }
                    System.out.println();
                }
                break THREE;

            } else {
                System.err.println("Incorrect input");
                System.exit(-1);
            }

        }

      }

    }

