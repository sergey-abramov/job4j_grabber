package ru.job4j.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingForStreet implements Parking {

    private int countForSedan;

    private int countForTruck;

    private final List<Car> cars;

    public ParkingForStreet(int countForSedan, int countForTruck, List<Car> cars) {
        this.countForSedan = countForSedan;
        this.countForTruck = countForTruck;
        this.cars = cars;
    }

    @Override
    public void add(Car car) {
        if (countForTruck == 0 && countForSedan == 0) {
            throw new IllegalArgumentException("Все места заняты");
        }
        if (car.size() > 1) {
            if (countForTruck > 0) {
                countForTruck--;
                cars.add(car);
            } else if (countForSedan >= car.size()) {
                cars.add(car);
                countForSedan = countForSedan - car.size();
            }
        } else if (car.size() == 1) {
            if (countForSedan == 0) {
                throw new IllegalArgumentException("Все места для легковых авто заняты");
            }
            countForSedan--;
            cars.add(car);
        }
    }

    @Override
    public List<Integer> getCount() {
        return List.of(countForSedan, countForTruck);
    }

}
