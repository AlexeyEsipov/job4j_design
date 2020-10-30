package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] container;
    private int indexFree;
    private int modCount = 0;

    public SimpleArray(int length) {
        this.container = new Object[length];
        this.indexFree = 0;
    }

    public SimpleArray() {
        this(10);
    }

    public void add(T model) {
        if (indexFree == container.length) {
            grow();
        }
        container[indexFree++] = model;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, indexFree);
        return (T) container[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new ContIterator<>();
    }

    private void grow() {
        container = Arrays.copyOf(container, container.length * 2);
        modCount++;
    }

    private class ContIterator<T> implements Iterator<T> {
        private int nextElement = 0;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return nextElement < indexFree;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return  (T) container[nextElement++];
        }
    }
}
