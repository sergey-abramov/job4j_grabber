package ru.job4j.lsp.parking;

import java.util.List;

public interface Parking {

    void add(Car car);

    List<Integer> getCount();
}
