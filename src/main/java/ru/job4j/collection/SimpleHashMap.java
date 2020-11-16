package ru.job4j.collection;

import java.util.Iterator;

public class SimpleHashMap<K,V> {
    private Object[] container;
    private int load = 10;
    private int indexFree;

    public SimpleHashMap() {
        this.container = new Object[10];
        this.indexFree = 0;
    }

    public boolean insert(K key, V value) {
        if (getIndex(key) != -1) {
            return false;
        }
        if (indexFree > load * 0.7) {
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
        Box el = (Box) container[index];
        return el.value;
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
        int code = key.hashCode();
        for (int i = 0; i < container.length; i++) {
            if (container[i] == null) {
                continue;
            }
            Box boxEl = (Box)container[i];
            if (boxEl.keyHashCode == code && boxEl.key.equals(key)) {
                return i;
            }
        }
        return -1;
    }

    private int hash(int code) {
        return code % container.length;
    }

//    private Iterator<Box> iterator() {
//        return new MapIterator<>();
//    }

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
