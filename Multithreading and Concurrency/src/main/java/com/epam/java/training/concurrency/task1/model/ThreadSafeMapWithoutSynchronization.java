package com.epam.java.training.concurrency.task1.model;

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ThreadSafeMapWithoutSynchronization<K, V> implements Map<K, V> {


    private final AtomicInteger count = new AtomicInteger(0);
    private final Node<K, V>[] buckets;
    private final Object[] locks;

    public ThreadSafeMapWithoutSynchronization(final int mapSize) {
        locks = new Object[mapSize];
        Arrays.fill(locks, new Object());
        buckets = (Node<K, V>[]) new Node[mapSize];
    }

    public V get(Object key) {
        if (key == null) {
            throw new IllegalArgumentException();
        } else {
            int hash = hash(key);
            synchronized (getLockFor(hash)) {
                Node<K, V> node = buckets[getBucketIndex(hash)];
                while (node != null) {
                    if (isKeyEquals(key, hash, node)) {
                        return node.value;
                    }
                    node = node.next;
                }
                return null;
            }
        }
    }

    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        } else {
            int hash = hash(key);
            synchronized (getLockFor(hash)) {
                int bucketIndex = getBucketIndex(hash);
                Node<K, V> node = buckets[bucketIndex];
                if (node == null) {
                    buckets[bucketIndex] = new Node<>(hash, key, value, null);
                    count.incrementAndGet();
                    return buckets[bucketIndex].value;
                } else {
                    Node<K, V> prevNode = node;
                    while (node != null) {
                        if (isKeyEquals(key, hash, node)) {
                            V prevValue = node.value;
                            node.value = value;
                            return prevValue;
                        }
                        prevNode = node;
                        node = node.next;
                    }
                    prevNode.next = new Node<>(hash, key, value, null);
                    count.incrementAndGet();
                    return null;
                }
            }
        }
    }

    @Override
    public V remove(Object key) {
        if (key == null) throw new IllegalArgumentException();
        int hash = hash(key);
        synchronized (getLockFor(hash(key))) {
            int bucketIndex = getBucketIndex(hash);
            Node<K, V> node = buckets[bucketIndex];
            if (node != null) {
                if (isKeyEquals(key, hash, node)) {
                    if (node.next != null) {
                        buckets[bucketIndex] = node.next;
                    } else {
                        buckets[bucketIndex] = null;
                    }
                    count.decrementAndGet();
                    return node.value;
                }
                Node<K, V> prevNode = node;
                while (node != null) {
                    if (isKeyEquals(key, hash, node)) {
                        V prevValue = node.value;
                        prevNode.next = node.next;
                        count.decrementAndGet();
                        return prevValue;
                    }
                    prevNode = node;
                    node = node.next;
                }
            }

            return null;
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        if (map == null) throw new IllegalArgumentException();
        map.forEach(this::put);
    }

    @Override
    public void clear() {
        Node[] nodes;
        if ((nodes = this.buckets) != null && this.count.get() > 0) {
            this.count.set(0);
            Arrays.fill(nodes, null);
        }
    }

    @Override
    public Set<K> keySet() {
        return new AbstractSet<K>() {
            @Override
            public Iterator<K> iterator() {
                return new Iterator<K>() {
                    private List<K> list = Stream.of(ThreadSafeMapWithoutSynchronization.this.buckets)
                            .map(node -> node.key)
                            .collect(Collectors.toList());
                    private int currentIndex = 0;

                    @Override
                    public boolean hasNext() {
                        return currentIndex < ThreadSafeMapWithoutSynchronization.this.size()
                                && list.get(currentIndex) != null;
                    }

                    @Override
                    public K next() {
                        return list.get(currentIndex++);
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }

            @Override
            public int size() {
                return ThreadSafeMapWithoutSynchronization.this.size();
            }
        };
    }

    @Override
    public Collection<V> values() {
        return new AbstractCollection<V>() {
            @Override
            public void clear() {
                ThreadSafeMapWithoutSynchronization.this.clear();
            }

            @Override
            public int size() {
                return ThreadSafeMapWithoutSynchronization.this.size();
            }

            @Override
            public boolean contains(Object v) {
                return ThreadSafeMapWithoutSynchronization.this.containsValue(v);
            }

            @Override
            public Iterator<V> iterator() {
                return new Iterator<V>() {
                    private List<V> list = Stream.of(ThreadSafeMapWithoutSynchronization.this.buckets)
                            .map(node -> node.value)
                            .collect(Collectors.toList());
                    private int currentIndex = 0;

                    @Override
                    public boolean hasNext() {
                        return currentIndex < ThreadSafeMapWithoutSynchronization.this.size()
                                && list.get(currentIndex) != null;
                    }

                    @Override
                    public V next() {
                        return list.get(currentIndex++);
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new AbstractSet<Entry<K, V>>() {
            @Override
            public Iterator<Entry<K, V>> iterator() {
                return new Iterator<Entry<K, V>>() {
                    private List<Entry<K, V>> list = Stream.of(ThreadSafeMapWithoutSynchronization.this.buckets)
                            .map(node -> new Entry<K, V>() {
                                @Override
                                public K getKey() {
                                    return node.key;
                                }

                                @Override
                                public V getValue() {
                                    return node.value;
                                }

                                @Override
                                public V setValue(V o) {
                                    return node.setValue(o);
                                }
                            })
                            .collect(Collectors.toList());
                    private int currentIndex = 0;

                    @Override
                    public boolean hasNext() {
                        return currentIndex < ThreadSafeMapWithoutSynchronization.this.size()
                                && list.get(currentIndex) != null;
                    }

                    @Override
                    public Entry<K, V> next() {
                        return list.get(currentIndex++);
                    }
                };
            }

            @Override
            public int size() {
                return ThreadSafeMapWithoutSynchronization.this.size();
            }
        };
    }

    @Override
    public int size() {
        return count.get();
    }

    @Override
    public boolean isEmpty() {
        return this.count.get() == new AtomicInteger(0).get();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.get(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        if (value == null) {
            throw new NullPointerException();
        } else {
            synchronized (buckets) {
                return Stream.of(buckets)
                        .map(node -> node.value)
                        .filter(v -> v.equals(value)).count() == 1;
            }
        }
    }


    private boolean isKeyEquals(Object key, int hash, Node<K, V> node) {
        return node.hash == hash &&
                node.key == key || (node.key.equals(key));
    }

    private int hash(Object key) {
        return key.hashCode();
    }

    private int getBucketIndex(int hash) {
        return hash % buckets.length;
    }

    private Object getLockFor(int hash) {
        return locks[hash % locks.length];
    }

    private static class Node<K, V> {
        final int hash;
        K key;

        V value;

        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        V setValue(V value) {
            this.value = value;
            return value;
        }

    }
}
