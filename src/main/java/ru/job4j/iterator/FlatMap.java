package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class FlatMap<T> implements Iterator<T> {
    private final Iterator<Iterator<T>> data;
    private Iterator<T> cursor;

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
        this.cursor = data.next(); // установка "курсора" перед начальной СТРОКОЙ (data.next()) -
    // берем начальный набор данных (по аналогии с двумерным массивом - строка с индексом 0)
    }

    @Override
    public boolean hasNext() {
        if (data.hasNext() && !cursor.hasNext()) { //если есть следующий набор данных,
                                                    // И в текущем наборе больше данных нет,
            cursor = data.next();           // то вводим в работу следующий набор данных
        }
        return cursor.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return cursor.next();
    }

    public static void main(String[] args) {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator(),
                List.of(4, 5, 6).iterator(),
                List.of(7, 8, 9).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        while (flat.hasNext()) {
        System.out.println(flat.next());
        }
    }
}
