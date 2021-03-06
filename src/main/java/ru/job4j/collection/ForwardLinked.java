package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
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

    public void revert() {
        if (head == null || head.next == null) {
            return;
        }
        Node<T> first = head;
        Node<T> second = head.next;
        Node<T> third = second.next;
        first.next = null;
        second.next = first;
        Node<T> current = third;
        Node<T> previous = second;
        while (current != null) {
            Node<T> next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        head = previous;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T value = head.value;
        Node<T> tmp = head.next;
        head.next = null;
        head = tmp;
        return value;
    }

    public T deleteLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> tmpHead = head;
        T value;
        if (head.next == null) {
            value = head.value;
            head = null;
            return value;
        }
        while (tmpHead.next.next != null) {
            tmpHead = tmpHead.next;
        }
        value = tmpHead.next.value;
        tmpHead.next = null;
        return value;
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
