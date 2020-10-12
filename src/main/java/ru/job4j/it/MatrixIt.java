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
        Integer result = null;
        for (int i = row; i < data.length; i++) {
            if (data[i].length == 0) {
                continue;
            }
            if (column <= data[i].length - 1) {
                result = data[i][column];
                if (column == data[i].length - 1) {
                    column = 0;
                    row = i + 1;
                    break;
                } else {
                    column++;
                    row = i;
                    break;
                }
            }
        }
        return result;
    }
}
