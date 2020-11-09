package com.epam.java.training.concurrency.task1.model;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Vladislav Zhelezov.
 */
public interface LockFreeList<T> {

    static <E> CopyOnWriteArrayList newList(final int fragmentSize) {
        return new CopyOnWriteArrayList();
    }


}
