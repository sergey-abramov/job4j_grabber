package ru.job4j.lsp.parking;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArrangementControlTest {

    @Test
    void arrangementForSedanAndTruckFull() {
        Parking parking = new ParkingForStreet(4, 3);
        ArrangementControl control = new ArrangementControl(parking);
        control.arrangement(new Sedan());
        control.arrangement(new Sedan());
        for (int i = 0; i < 4; i++) {
            control.arrangement(new Truck());
        }
        assertThat(parking.getCount()).isEqualTo(List.of(0,0));
    }

    @Test
    void arrangementForSedanAndTruckNotFull() {
        Parking parking = new ParkingForStreet(4, 3);
        ArrangementControl control = new ArrangementControl(parking);
        control.arrangement(new Sedan());
        for (int i = 0; i < 3; i++) {
            control.arrangement(new Truck());
        }
        assertThat(parking.getCount()).isEqualTo(List.of(3,0));
    }

    @Test
    void exceptionArrangement() {
        Parking parking = new ParkingForStreet(4, 3);
        ArrangementControl control = new ArrangementControl(parking);
        control.arrangement(new Sedan());
        control.arrangement(new Sedan());
        for (int i = 0; i < 4; i++) {
            control.arrangement(new Truck());
        }
        assertThatThrownBy(() -> control.arrangement(new Truck()))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void exceptionArrangementForSedan() {
        Parking parking = new ParkingForStreet(2, 3);
        ArrangementControl control = new ArrangementControl(parking);
        control.arrangement(new Sedan());
        control.arrangement(new Sedan());
        for (int i = 0; i < 2; i++) {
            control.arrangement(new Truck());
        }
        assertThatThrownBy(() -> control.arrangement(new Sedan()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}