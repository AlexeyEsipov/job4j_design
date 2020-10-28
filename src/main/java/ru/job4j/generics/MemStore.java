package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        int index = findIndexById(id);
        if (index == -1) {
            return false;
        } else {
            mem.set(index, model);
            return true;
        }
    }

    @Override
    public boolean delete(String id) {
        int index = findIndexById(id);
        if (index == -1) {
            return false;
        } else {
            mem.remove(index);
            return true;
        }
    }

    @Override
    public T findById(String id) {
        return mem.stream()
                  .filter(Objects::nonNull)
                  .filter(e -> e.getId().equals(id))
                  .findFirst().orElse(null);
    }

    private int findIndexById(String id) {
        int count = -1;
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i) != null && mem.get(i).getId().equals(id)) {
                count = i;
                break;
            }
        }
        return count;
    }
}
