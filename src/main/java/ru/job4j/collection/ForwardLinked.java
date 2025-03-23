package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        if (head == null) {
            head = new Node<>(value, null);
        } else {
            Node<T> tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = new Node<>(value, null);
        }
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
    }

    public boolean revert() {
        boolean rsl = head != null && head.next != null;
        if (rsl) {
            Node<T> previous = null;
            Node<T> current = head;
            while (current != null) {
                Node<T> next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }
            head = previous;
        }
        return rsl;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T value = head.value;
        Node<T> tmp = head.next;
        head.next = null;
        head.value = null;
        head = tmp;
        return value;
    }

    public T deleteLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T result;
        if (head.next == null) {
            result = head.value;
            head = null;
        } else {
            Node<T> tmpHead = head;
            while (tmpHead.next.next != null) {
                tmpHead.next = tmpHead.next.next;
            }
            result = tmpHead.next.value;
            tmpHead.next = null;
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}

/*

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private int size = 0;

    public void add(T value) {
        size++;
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        size--;
        T ref = head.value;
        Node<T> temp = head;
        head = head.next;
        temp.value = null;
        temp.next = null;
        return ref;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
        size++;
    }

    public boolean revert() {
        boolean rsl = false;
        if (head == null || head.next == null) {
            return rsl;
        }
        Node<T> current = head.next;
        head.next = null;
        while (current != null) {
            Node<T> next = current.next;
            current.next = head;
            head = current;
            current = next;
        }
        rsl = true;
        return rsl;
    }

    public void insert(int index, T item) {
        Objects.checkIndex(index, size);
        Node<T> n = findNodeByIndex(index);
        n.next = new Node<>(item, n.next);
        size++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> n = head;
        for (int i = 0; i < index; i++) {
            n = n.next;
        }
        return n.value;
    }

    public T set(int index, T item) {
        Objects.checkIndex(index, size);
        T res = null;
        if (index == 0) {
            res = head.value;
            head.value = item;
        }
        if (index > 0) {
            Node<T> n = findNodeByIndex(index);
            res = n.value;
            n.value = item;
        }
        return res;
    }

    public T remove(int index) {
        Objects.checkIndex(index, size);
        T res = null;
        if (index == 0) {
            res = deleteFirst();
        }
        if (index > 0) {
            Node<T> n = findNodeByIndex(index - 1);
            Node<T> nn = n.next;
            res = nn.value;
            n.next = nn.next;
            nn.value = null;
            if (nn.next != null) {
                nn.next = null;
            }
            size--;
        }
        return res;
    }

    private Node<T> findNodeByIndex(int index) {
        Node<T> n = head;
        for (int i = 0; i < index; i++) {
            n = n.next;
        }
        return n;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
 */
