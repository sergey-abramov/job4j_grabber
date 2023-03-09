package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        TreeSet<T> set = new TreeSet<>(comparator);
        set.addAll(value);
        return set.last();
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        TreeSet<T> set = new TreeSet<>(comparator);
        set.addAll(value);
        return set.first();
    }
}
