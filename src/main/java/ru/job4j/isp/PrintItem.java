package ru.job4j.isp;

public class PrintItem implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        menu.forEach(i -> {
            String[] strings = i.getNumber().split("\\.");
            if (strings.length == 0) {
                System.out.println(i.getNumber() + i.getName());
            }
            if (strings.length == 1) {
                System.out.printf("\t%s%s%n", i.getNumber(), i.getName());
            }
            if (strings.length == 2) {
                System.out.printf("\t\t%s%s%n", i.getNumber(), i.getName());
            }
            if (strings.length == 3) {
                System.out.printf("\t\t\t%s%s%n", i.getNumber(), i.getName());
            }
        });
    }
}
