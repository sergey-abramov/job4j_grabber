package ru.job4j.lsp;

public class Valid {

    static class Passport {
        private int ser;
        private int number;

        public Passport(int ser, int number) {
            this.ser = ser;
            this.number = number;
        }

        public int getSer() {
            return ser;
        }

        public int getNumber() {
            return number;
        }

        protected void valid(Passport passport) {
            if (passport.getSer() < 1 && passport.getSer() > 9999) {
                throw new IllegalArgumentException();
            }
            if (passport.getNumber() < 1 && passport.getNumber() > 999999) {
                throw new IllegalArgumentException();
            }
        }

        public Passport setPassport(Passport passport) {
            valid(passport);
            return passport;
        }
    }

    static class People extends Passport {

        public People(int ser, int number) {
            super(ser, number);
        }

        @Override
        public Passport setPassport(Passport passport) {
            return super.setPassport(passport);
        }
    }
}
