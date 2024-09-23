package ex00;

public class Program {
    public static void main(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--count=")) {
            System.out.println("Instruction: java Program --count=<number>");
            return;
        }
        String[] parts = args[0].split("=");
        int count = Integer.valueOf(parts[1]);

        Thread eggThread = new Thread(new EggRunnable(count));
        Thread henThread = new Thread(new HenRunnable(count));

        eggThread.start();
        henThread.start();


        try {
            eggThread.join();
            henThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("The main thread has been interrupted.");
        }

        for (int i = 0; i < count; i++) {
            System.out.println("Human");
        }
    }
}
