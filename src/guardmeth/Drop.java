package guardmeth;

public class Drop {
    private int integerMessage;
    private boolean empty = true;

    public synchronized int take() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException ignored) {}
        }
        empty = true;
        notifyAll();
        return integerMessage;
    }

    public synchronized void put(int message) {
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException ignored) {}
        }
        empty = false;
        this.integerMessage = message;
        notifyAll();
    }
}
