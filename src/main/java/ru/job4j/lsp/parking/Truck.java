package ru.job4j.lsp.parking;

public class Truck implements Car {

    private final int size;

    public Truck(int size) {
        if (size <= 1) {
            throw new IllegalArgumentException("Размер грузового автомобиля должен быть больше");
        }
        this.size = size;
    }

    @Override
    public int size() {
        return size;
    }
}
