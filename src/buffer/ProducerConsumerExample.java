package buffer;

public class ProducerConsumerExample {
    public static void main(String[] args) {
        Drop drop = new Drop(1000);
        (new Thread(new Producer(drop, 1000))).start();
        (new Thread(new Consumer(drop, 1000))).start();
    }
}
