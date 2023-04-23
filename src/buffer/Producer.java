package buffer;

import java.util.Random;

public class Producer implements Runnable {
    private final Drop drop;
    private final int numbersToProduce;

    public Producer(Drop drop, int numbersToProduce) {
        this.drop = drop;
        this.numbersToProduce = numbersToProduce;
    }

    public void run() {
        Random random = new Random();
        for (int i = 0; i < numbersToProduce; i++) {
            int produced = random.nextInt(10000);
            System.out.println("Produced: " + produced);
            drop.put(produced);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}