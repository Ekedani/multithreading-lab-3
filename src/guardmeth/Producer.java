package guardmeth;

import java.util.Random;

public class Producer implements Runnable {
    private final Drop drop;
    private final int infoLength;

    public Producer(Drop drop, int infoLength) {
        this.drop = drop;
        this.infoLength = infoLength;
    }

    public void run() {
        Random random = new Random();
        int[] numbersInfo = random.ints(infoLength, 0,10000).toArray();;

        for (int i : numbersInfo) {
            drop.put(i);
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException ignored) {
            }
        }
        drop.put(-1);
    }
}
