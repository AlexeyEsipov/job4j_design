package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements SimpleList<T> {
    private T[] container;
    private int size = 0;
    private int modCount;

    public SimpleArray(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    public SimpleArray() {
        this(0);
    }

    @Override
    public void add(T model) {
        if (size == container.length) {
            grow();
        }
        container[size++] = model;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T oldValue = get(index);
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        T value = get(index);
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        container[--size] = null;
        modCount++;
        return value;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int index = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return  container[index++];
            }
        };
    }

    private void grow() {
        if (container.length == 0) {
            container = Arrays.copyOf(container, 1);
        } else {
            container = Arrays.copyOf(container, container.length * 2);
        }
        modCount++;
    }

    public static void main(String[] args) {
        SimpleArray<Integer> sa = new SimpleArray<>(6);
        sa.add(1);
        sa.add(2);
        sa.add(3);
        sa.add(4);
        sa.add(5);
        sa.add(6);
        sa.remove(2);

    }
}
