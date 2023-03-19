package ru.job4j.isp;

import java.util.Scanner;

public class TodoApp {

    private static final String MENU = """
            1. Добавить пункт меню
            2. Добавить подпункт меню
            3. Выбрать пункт меню
            4. Печать меню
            5. Выйти
            """;
    private static final int ADD_ROOT = 1;
    private static final int ADD_CHILD = 2;
    private static final int SELECT = 3;
    private static final int PRINT = 4;
    private static final int EXIT = 5;
    private static final String ASK = "Выберите пункт меню";
    private static final ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action");

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        Scanner scanner = new Scanner(System.in);
        MenuPrinter printer = new PrintItem();
        boolean run = true;
        String name;
        while (run) {
            System.out.println(MENU);
            System.out.println(ASK);
            int select = Integer.parseInt(scanner.nextLine());
            if (select == ADD_ROOT) {
                System.out.println("Введите имя пункта");
                name = scanner.nextLine();
                menu.add(Menu.ROOT, name, DEFAULT_ACTION);
            } else if (select == ADD_CHILD) {
                System.out.println("Введите имя пункта");
                name = scanner.nextLine();
                System.out.println("Введите имя подпункта");
                String child = scanner.nextLine();
                menu.add(name, child, DEFAULT_ACTION);
            } else if (select == SELECT) {
                DEFAULT_ACTION.delegate();
            } else if (select == PRINT) {
                printer.print(menu);
            } else if (select == EXIT) {
                run = false;
            }
        }
    }
}
