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
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int index = indexFor(hash(key));
        boolean rsl = Objects.isNull(table[index]);
        if (rsl) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(K key) {
        int result = 0;
        if (!Objects.isNull(key)) {
            int h = key.hashCode();
            result = h ^ (h >>> 16);
        }
        return result;
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] tempTable = new MapEntry[capacity];
        for (MapEntry<K, V> entry : table) {
            if (!Objects.isNull(entry)) {
                tempTable[indexFor(hash(entry.key))] = entry;
            }
        }
        modCount++;
        table = tempTable;
    }

    @Override
    public V get(K key) {
        V result = null;
        int index = indexFor(hash(key));
        boolean isCellNotNull = !Objects.isNull(table[index]);
        if (isCellNotNull) {
            K keyTable = table[index].key;
            if (Objects.isNull(keyTable) && Objects.isNull(key)) {
                result = table[index].value;
            }
            if (!Objects.isNull(keyTable) && !Objects.isNull(key)
                    && table[index].key.hashCode() == key.hashCode()
                    && table[index].key.equals(key)) {
                result = table[index].value;
            }

        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = indexFor(hash(key));
        boolean isCellNotNull = !Objects.isNull(table[index]);
        if (isCellNotNull) {
            K keyTable = table[index].key;
            if ((Objects.isNull(keyTable) && Objects.isNull(key))
                    || (!Objects.isNull(keyTable)
                    && !Objects.isNull(key)
                    && (table[index].key.hashCode() == key.hashCode()
                    && table[index].key.equals(key)))) {
                table[index] = null;
                result = true;
                count--;
                modCount++;
            }

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

    private static class MapEntry<K, V> {
        private K key;
        private V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}