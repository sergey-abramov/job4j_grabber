package ru.job4j.ocp;

public class Animal {

    private static class Dog {
        public String sound() {
            return "гав - гав";
        }
    }

    private static class Monkey extends Dog {
        @Override
        public String sound() {
            return super.sound();
        }
    }
}
