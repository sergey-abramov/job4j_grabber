package ru.job4j.lsp;

public class Animal {

    static class Cat {
        private int age;

        public Cat(int age) {
            this.age = age;
        }

        public void hunt(int age) {
            if (age < 1) {
                throw new IllegalArgumentException();
            }
        }
    }

    static class Tiger extends Cat {

        public Tiger(int age) {
            super(age);
        }

        @Override
        public void hunt(int age) {
            if (age < 2) {
                throw new IllegalArgumentException();
            }
        }
    }

    static class Puma extends Cat {

        public Puma(int age) {
            super(age);
        }

        @Override
        public void hunt(int age) {
            if (age < 5) {
                throw new IllegalArgumentException();
            }
        }
    }
}
