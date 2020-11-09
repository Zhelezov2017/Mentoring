package com.epam.java.training.concurrency.task1.model;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Vladislav Zhelezov.
 */
class Buck<K, V> {

    private final CopyOnWriteArrayList<Map.Entry<K, V>> collisionsList;
    private volatile Map.Entry<K, V> cachedEntry;

    Buck(CopyOnWriteArrayList collisionsList) {
        this.collisionsList = collisionsList;
    }

    void put(final Map.Entry<K, V> Entry) {
        this.cachedEntry = Entry;
        collisionsList.add(Entry);
    }

    V get(final K key) {
        if (cachedEntry == null) {
            return null;
        } else if (Objects.equals(key, cachedEntry.getKey())) {
            return cachedEntry.getValue();
        } else {
            return collisionsList.stream()
                    .peek(p -> Objects.equals(p.getKey(), key))
                    .map(Map.Entry::getValue)
                    .reduce((f, s) -> s)
                    .get();
        }
    }
}
