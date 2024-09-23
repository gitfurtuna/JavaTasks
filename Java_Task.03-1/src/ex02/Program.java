package ex02;

import java.util.ArrayList;
import java.util.Random;

public class Program {
    private static final Object lock = new Object();
    private static int result = 0;

    public static void main(String[] args) {
        if (args.length != 2 || !args[0].startsWith("--arraySize=") || !args[1].startsWith("--threadsCount=")) {
            System.out.println("Instruction: java Program --arraySize=<number> --threadsCount=<number>");
            return;
        }

        int arraySize = Integer.valueOf(args[0].split("=")[1]);
        int threadsCount = Integer.valueOf(args[1].split("=")[1]);

        if (arraySize <= 0 || arraySize > 2000000 || threadsCount <= 0 || threadsCount > arraySize) {
            System.out.println("Invalid parameters. Make sure that the array value is greater than zero and less than 2 000 000, and that the number of threads is greater than zero and does not exceed the size of the array.");
            return;
        }

        ArrayList<Integer> arr = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < arraySize; i++) {
            arr.add(random.nextInt(2001) - 1000);
        }

        int sum = arr.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Sum: " + sum);

        int sectionSize = (arraySize + threadsCount - 1)/ threadsCount;
        Thread[] threads = new Thread[threadsCount];

        for (int i = 0; i < threadsCount; i++) {
            final int start = i * sectionSize;
            final int end = (i == threadsCount - 1) ? arraySize : (i + 1) * sectionSize;

            threads[i] = new Thread(() -> {
                int totalSum = arr.stream().skip(start).limit(end - start).mapToInt(Integer::intValue).sum();
                synchronized (lock) {
                    result += totalSum;
                }

                System.out.println("Thread " + (start / sectionSize + 1) + ": from " + start + " to " + (end - 1) + " sum is " + totalSum);
            });
            threads[i].start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.err.println("Thread was interrupted.");
        }

        System.out.println("Sum by threads: " + result);
    }



    }

