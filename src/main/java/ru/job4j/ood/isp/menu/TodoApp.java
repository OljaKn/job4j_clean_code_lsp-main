package ru.job4j.ood.isp.menu;

import java.util.Optional;
import java.util.Scanner;

/**
 * 6. Создайте простенький класс TodoApp. Этот класс будет представлять собой консольное приложение, которое позволяет:
 * Добавить элемент в корень меню;
 * Добавить элемент к родительскому элементу;
 * Вызвать действие, привязанное к пункту меню (действие можно сделать константой,
 * например, ActionDelete DEFAULT_ACTION = () -> System.out.println("Some action") и указывать при добавлении элемента в меню);
 * Вывести меню в консоль.
 */
public class TodoApp {
    private static final ActionDelegate DEFAULT_ACTION = System.out::println;
    private static final int ADD_ITEM_ROOT = 1;
    private static final int ADD_ITEM_PARENT = 2;
    private static final int CALL_ACTION = 3;
    private static final int PRINT_MENU = 4;
    private static final String MENU = "Выберите пунтк меню:\n"
            + "1. Добавить элемент в корень меню.\n"
            + "2. Добавить элемент к родительскому элементу.\n"
            + "3. Вызвать действие, привязанное к пункту меню.\n"
            + "4. Вывести меню в консоль.\n"
            + "5. Для выхода введите любое число.";
    private Menu menu;
    private Scanner scanner;

    public TodoApp(Menu menu, Scanner scanner) {
        this.menu = menu;
        this.scanner = scanner;
    }

    public static void main(String[] args) {
        TodoApp todoApp = new TodoApp(new SimpleMenu(), new Scanner(System.in));
        Printer printer = new Printer();
        boolean flag = true;
        while (flag) {
            System.out.println(MENU);
            int request = Integer.parseInt(todoApp.scanner.nextLine());
            if (request == ADD_ITEM_ROOT) {
                System.out.println("Введите название пункта меню: ");
                String name = todoApp.scanner.nextLine();
                todoApp.menu.add(Menu.ROOT, name, DEFAULT_ACTION);
            } else if (request == ADD_ITEM_PARENT) {
                System.out.println("Введите название пункта, к кторому хотите добавить подпункт: ");
                String nameParent = todoApp.scanner.nextLine();
                System.out.println("Введите название подпункта: ");
                String nameChild = todoApp.scanner.nextLine();
                todoApp.menu.add(nameParent, nameChild, DEFAULT_ACTION);
            } else if (request == CALL_ACTION) {
                System.out.println("Введите название пункта, которое хотите выполнить: ");
                String nameAction = todoApp.scanner.nextLine();
                Optional<Menu.MenuItemInfo> itemInfo = todoApp.menu.select(nameAction);
                itemInfo.get().getActionDelegate().delegate();
            } else if (request == PRINT_MENU) {
                System.out.println("Вывод меню: ");
                printer.print(todoApp.menu);
            } else {
                flag = false;
            }
        }
    }
}
