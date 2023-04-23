package buffer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Drop {
    private final int[] buffer;
    private int elementsInBuffer = 0;

    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    public Drop(int bufferSize) {
        buffer = new int[bufferSize];
    }

    public int take() {
        lock.lock();
        try {
            while (elementsInBuffer < 1) {
                notEmpty.await();
            }
            elementsInBuffer--;
            notFull.signalAll();
            return buffer[elementsInBuffer];
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void put(int element) {
        lock.lock();
        try {
            while (elementsInBuffer >= buffer.length) {
                notFull.await();
            }
            buffer[elementsInBuffer] = element;
            elementsInBuffer++;
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
