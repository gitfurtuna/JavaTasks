package ex01;

import java.util.LinkedList;

public class HenRunnable implements Runnable {
    private final int count;
    private  final LinkedList<String> buffer;


    public HenRunnable(int count, LinkedList<String> buffer) {
        this.count = count;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            synchronized (buffer) {
                while (!buffer.isEmpty() && buffer.getLast().equals("Hen")) {
                    try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                buffer.add("Hen");
                System.out.println("Hen");
                buffer.notify();

            }
        }
    }
}
