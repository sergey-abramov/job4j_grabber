package ru.job4j.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingForStreet implements Parking {

    private final int countForSedan;

    private final int countForTruck;

    public ParkingForStreet(int countForSedan, int countForTruck) {
        this.countForSedan = countForSedan;
        this.countForTruck = countForTruck;
    }

    @Override
    public void add() {

    }

    @Override
    public List<Integer> getCount() {
        return List.of(countForSedan, countForTruck);
    }
}
