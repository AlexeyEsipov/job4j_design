package ru.job4j.generics;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private final Object[] container;
    private int indexFree;

    public SimpleArray(int length) {
        this.container = new Object[length];
        this.indexFree = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ContIterator<>();
    }

    public void add(T model) {
        container[indexFree++] = model;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, indexFree);
        container[index] = model;
    }

    public T get(int index) {
        Objects.checkIndex(index, indexFree);
        return (T) container[index];
    }

    public void remove(int index) {
        Objects.checkIndex(index, indexFree);
        if (index < indexFree - 1) {
            System.arraycopy(container, index + 1, container, index, indexFree - index);
        }
        container[indexFree - 1] = null;
        indexFree--;
    }

    private class ContIterator<T> implements Iterator<T> {
        private int nextElement = 0;

        @Override
        public boolean hasNext() {
            return nextElement < indexFree;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return  (T) container[nextElement++];
        }
    }
}
