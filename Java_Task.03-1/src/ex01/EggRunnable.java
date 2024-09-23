package ex01;

import java.util.LinkedList;

public class EggRunnable implements Runnable {
    private final int count;

    private final LinkedList<String> buffer;

    public EggRunnable(int count, LinkedList<String> buffer) {
        this.count = count;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            synchronized (buffer) {
                while (!buffer.isEmpty() && buffer.getLast().equals("Egg")) {
                    try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                buffer.add("Egg");
                System.out.println("Egg");
                buffer.notify();

            }
        }
    }
}
