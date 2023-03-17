package ru.job4j.lsp.parking;

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
}
