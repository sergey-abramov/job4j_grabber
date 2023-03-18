package ru.job4j.lsp.parking;

public class Sedan implements Car {

    private static final int SIZE = 1;

    @Override
    public int size() {
        return SIZE;
    }
}
