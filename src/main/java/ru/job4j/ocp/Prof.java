package ru.job4j.ocp;

public class Prof {

    private static class Developer {
        public String doIt() {
            return "coding";
        }
    }

    private static class Mechanic extends Developer {
        @Override
        public String doIt() {
            return super.doIt();
        }
    }
}
