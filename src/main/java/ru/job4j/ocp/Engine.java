package ru.job4j.ocp;

public class Engine {

    private static class Ford {
        private static final int FUEL_OCTAN = 95;

        public int refueling() {
            return FUEL_OCTAN;
        }

        public String engine() {
            return "gasoline";
        }
    }

    private static class Tesla extends Ford {
        @Override
        public int refueling() {
            return super.refueling();
        }

        @Override
        public String engine() {
            return super.engine();
        }
    }
}
