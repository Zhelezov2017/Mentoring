package com.epam.java.training.concurrency.task4.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Vladislav Zhelezov.
 * <p>
 * Pool that block when it has not any items or it full
 */
public class BlockingObjectPool {

    private final int size;
    private final List<Object> objectList;
    private static final int MAX_CAPPACITY = 1000;
    private final ReentrantLock lock;
    private AtomicInteger count = new AtomicInteger(0);
    private final Object[] items;
    private int takeIndex;
    private int putIndex;
    private final Condition notFull;
    private final Condition notEmpty;

    /**
     * Creates filled pool of passed size
     *
     * @param size of pool
     */
    public BlockingObjectPool(int size) {
        this.size = size;
        this.lock = new ReentrantLock(false);
        this.items = new Object[size];
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
        if (size > MAX_CAPPACITY) {
            throw new IndexOutOfBoundsException();
        }
        objectList = new ArrayList<>(size);
    }

    /**
     * Gets object from pool or blocks if pool is empty
     *
     * @return object from pool
     */
    public Object get() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        try {
            lock.lockInterruptibly();
            Object e;
            while ((e = items[takeIndex]) == null)
                notEmpty.await();
            items[takeIndex] = null;
            if (++takeIndex == items.length) takeIndex = 0;
            count.getAndDecrement();
            return e;
        } finally {
            notFull.signal();
            lock.unlock();
        }
    }

    /**
     * Puts object to pool or blocks if pool is full
     *
     * @param object to be taken back to pool
     */
    public void take(Object object) {
        synchronized (objectList) {
            Objects.requireNonNull(object);
            final ReentrantLock lock = this.lock;
            try {
                lock.lockInterruptibly();
                while (count.get() == items.length)
                    notFull.await();
                final Object[] items = this.items;
                items[putIndex] = object;
                if (++putIndex == items.length) putIndex = 0;
                count.getAndIncrement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                notEmpty.signal();
                lock.unlock();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockingObjectPool that = (BlockingObjectPool) o;
        return Objects.equals(objectList, that.objectList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectList);
    }

    @Override
    public String toString() {
        return "BlockingObjectPool{" +
                "objectList=" + objectList +
                '}';
    }
}
