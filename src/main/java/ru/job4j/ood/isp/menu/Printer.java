package ru.job4j.ood.isp.menu;

public class Printer implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo itemInfo : menu) {
            System.out.println("-".repeat(itemInfo.getNumber().length())
                    + itemInfo.getNumber()
                    + itemInfo.getName());
        }
    }
}
