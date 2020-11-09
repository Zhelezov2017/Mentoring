package com.epam.java.training.concurrency.task1.model;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by Vladislav Zhelezov.
 */
public class ThreadSafeMapWithSynchronization<K, V> implements Map<K, V> {

    private final Map<K, V> providerMap;
    private final Object monitor;

    public ThreadSafeMapWithSynchronization(Map<K, V> map) {
        this.providerMap = map;
        monitor = this;
    }


    @Override
    public int size() {
        synchronized (monitor) {
            return providerMap.size();
        }
    }

    @Override
    public boolean isEmpty() {
        synchronized (monitor) {
            return providerMap.isEmpty();
        }
    }

    @Override
    public boolean containsKey(Object o) {
        synchronized (monitor) {
            return providerMap.containsKey(o);
        }
    }

    @Override
    public boolean containsValue(Object o) {
        synchronized (monitor) {
            return providerMap.containsValue(o);
        }
    }

    @Override
    public V get(Object o) {
        synchronized (monitor) {
            return providerMap.get(o);
        }
    }

    @Override
    public V put(K key, V value) {
        synchronized (monitor) {
            return providerMap.put(key, value);
        }
    }

    @Override
    public V remove(Object o) {
        synchronized (monitor) {
            return providerMap.remove(o);
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        synchronized (monitor) {
            providerMap.putAll(map);
        }
    }

    @Override
    public void clear() {
        synchronized (monitor) {
            providerMap.clear();
        }
    }

    @Override
    public Set<K> keySet() {
        synchronized (monitor) {
            return providerMap.keySet();
        }
    }

    @Override
    public Collection<V> values() {
        synchronized (monitor) {
            return providerMap.values();
        }
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        synchronized (monitor) {
            return providerMap.entrySet();
        }
    }
}
