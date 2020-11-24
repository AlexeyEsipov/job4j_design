package ru.job4j.collection;

import java.util.Iterator;

public class SimpleHashMap<K,V> {
    private Object[] container;
    private int load = 10;
    private int indexFree;
    private final double LOAD_FACTOR = 0.7;

    public SimpleHashMap() {
        this.container = new Object[10];
        this.indexFree = 0;
    }

    public boolean insert(K key, V value) {
        if (container[hash(key.hashCode())] != null) {
            return false;
        }
        if (indexFree > load * LOAD_FACTOR) {
            grow();
        }
        container[hash(key.hashCode())] = new Box(key, value);
        ++indexFree;
        return true;
    }

    public V get(K key) {
        int index = getIndex(key);
        if (index == -1) {
            return null;
        }
        return ((Box) container[index]).value;
    }

    public boolean delete(K key) {
        int index = getIndex(key);
        if (index == -1) {
            return false;
        }
        container[index] = null;
        --indexFree;
        return true;
    }

    private class Box {
        K key;
        int keyHashCode;
        V value;
        Box(K key, V value) {
            this.key = key;
            this.keyHashCode = key.hashCode();
            this.value = value;
        }
    }

    private int getIndex(K key) {
        int index = hash(key.hashCode());
        if (container[index] == null) {
            return -1;
        }
        Box boxEl = (Box)container[index];
        if (boxEl.keyHashCode == key.hashCode() && boxEl.key.equals(key)) {
            return index;
        }
        return -1;
    }

    private int hash(int code) {
        return code % container.length;
    }

    private class MapIterator<Box> implements Iterator{
        int indexNext = 0;

        @Override
        public boolean hasNext() {
            for (int i = indexNext; i < container.length; i++) {
                if (container[i] != null) {
                    indexNext = i;
                    return true;
                }
            }
            return false;
        }

        @Override
        public Box next() {
            return (Box)container[indexNext];
        }
    }

    private void grow(){
        Object[] newContainer = new Object[load * 2];
        Iterator<Box> iterator = new MapIterator<>();
        while (iterator.hasNext()) {
            Box el = iterator.next();
            int index = el.keyHashCode % newContainer.length;
            newContainer[index] = el;
        }
    }
}
