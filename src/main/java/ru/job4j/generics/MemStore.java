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
        T obj = findById(id);
        if (obj != null) {
         int index = mem.indexOf(obj);
         return mem.set(index, model).equals(obj);
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        T obj = findById(id);
        if (obj != null) {
            return mem.removeIf(e -> e.getId().equals(id));
        } else {
            return false;
        }
    }

    @Override
    public T findById(String id) {
        return mem.stream()
                  .filter(Objects::nonNull)
                  .filter(e -> e.getId().equals(id))
                  .findFirst().orElse(null);
    }
}
