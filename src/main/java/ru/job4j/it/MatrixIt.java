package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (int i = row; i < data.length; i++) {
            if (data[i].length == 0) {
                continue;
            }
            if (column <= data[i].length - 1) {
                result = true;
                row = i;
                break;
            }
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer result = data[row][column];
                if (column == data[row].length - 1) {
                    row++;
                    column = 0;
                } else {
                    column++;
                }
        return result;
    }
}
