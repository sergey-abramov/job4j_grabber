package ru.job4j.lsp;

public class Transport {

    static class Wheels {
        private int count;

        public Wheels(int count) {
            this.count = count;
        }

        public boolean transport(int count) {
            if (count < 1) {
                throw new IllegalArgumentException();
            }
            return true;
        }
    }

    static class Mono extends Wheels {

        public Mono(int count) {
            super(count);
        }

        @Override
        public boolean transport(int count) {
            return super.transport(count);
        }
    }

    static class Bus extends Wheels {

        public Bus(int count) {
            super(count);
        }

        @Override
        public boolean transport(int count) {
            if (count < 4) {
                throw new IllegalArgumentException();
            }
            return super.transport(count);
        }
    }
}
