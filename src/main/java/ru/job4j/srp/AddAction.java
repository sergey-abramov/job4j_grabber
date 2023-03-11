package ru.job4j.srp;

import java.util.List;

public interface AddAction<T> {
    T add(T value, List<T> list);

    boolean delete(T value, List<T> list);
}
