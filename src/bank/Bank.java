package bank;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Bank {
    public static final int NTEST = 10000;
    private final int[] accounts;
    private long ntransacts = 0;

    final Object lockedObject = new Object();
    final Lock reentrantLock = new ReentrantLock();
    final Condition notEmpty = reentrantLock.newCondition();

    // Replaced for with Array.fill, deleted ntransacts = 0
    public Bank(int n, int initialBalance) {
        accounts = new int[n];
        Arrays.fill(accounts, initialBalance);
    }

    public synchronized void transferSynchronized(int from, int to, int amount) {
        while (accounts[from] < amount) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        accounts[from] -= amount;
        accounts[to] += amount;
        ntransacts++;
        notifyAll();
        if (ntransacts % NTEST == 0) {
            test();
        }
    }

    public void transferSynchronizedBlock(int from, int to, int amount) {
        synchronized (lockedObject) {
            while (accounts[from] < amount) {
                try {
                    lockedObject.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            accounts[from] -= amount;
            accounts[to] += amount;
            ntransacts++;
            lockedObject.notifyAll();
            if (ntransacts % NTEST == 0) {
                test();
            }
        }
    }

    public void transferLocked(int from, int to, int amount) {
        reentrantLock.lock();
        try {
            while (accounts[from] < amount) {
                notEmpty.await();
            }
            accounts[from] -= amount;
            accounts[to] += amount;
            ntransacts++;
            notEmpty.signalAll();
            if (ntransacts % NTEST == 0) {
                test();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            reentrantLock.unlock();
        }
    }

    public void test() {
        int sum = 0;
        // Changed for loop
        for (int account : accounts) {
            sum += account;
        }
        System.out.println("Transactions:" + ntransacts + " Sum: " + sum);
    }

    // Implemented non-existing method size
    public int size() {
        return accounts.length;
    }
}

