package ru.job4j.tree;
import java.util.*;
import java.util.function.Predicate;

public class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> iParent = findBy(parent);
        Optional<Node<E>> iChild = findBy(child);
        if (iParent.isPresent() && iChild.isEmpty()) {
            iParent.get().getChildren().add(new Node<>(child));
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return search(e -> e.getValue().equals(value));
    }

    public boolean isBinary() {
        return search(e -> e.getChildren().size() > 2).isEmpty();
    }

    private Optional<Node<E>> search(Predicate<Node<E>> filter) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (filter.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.getChildren());
        }
        return rsl;
    }
}
