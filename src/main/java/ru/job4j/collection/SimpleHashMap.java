package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleHashMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;
    @SuppressWarnings("unchecked")
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int index = getIndex(key);
        boolean rsl = Objects.isNull(table[index]);
        if (rsl) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return rsl;
    }

    private int getIndex(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    @SuppressWarnings("unchecked")
    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] tempTable = new MapEntry[capacity];
        for (MapEntry<K, V> entry : table) {
            if (Objects.nonNull(entry)) {
                tempTable[getIndex(entry.key)] = entry;
            }
        }
        modCount++;
        table = tempTable;
    }

    @Override
    public V get(K key) {
        V result = null;
        int index = getIndex(key);
        if (isKeyEqualsKey(key, index)) {
            result = table[index].value;
        }
        return result;
    }

    private boolean isKeyEqualsKey(K key, int index) {
        return Objects.nonNull(table[index])
                && Objects.hashCode(key) == Objects.hashCode(table[index].key)
                && Objects.equals(key, table[index].key);
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = getIndex(key);
        if (isKeyEqualsKey(key, index)) {
            table[index] = null;
            result = true;
            count--;
            modCount++;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int index = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity && table[index] == null) {
                    index++;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private record MapEntry<K, V>(K key, V value) {
    }
}