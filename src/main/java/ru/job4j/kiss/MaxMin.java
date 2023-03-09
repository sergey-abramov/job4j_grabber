package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class MaxMin {

    private <T> TreeSet<T> find(List<T> value, Comparator<T> comparator) {
        TreeSet<T> set = new TreeSet<>(comparator);
        set.addAll(value);
        return set;
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return find(value, comparator).last();
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return find(value, comparator).first();
    }
}
