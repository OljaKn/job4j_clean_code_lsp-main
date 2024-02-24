package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PrinterTest {
@Test
    void whenPrint() {
    ActionDelegate action = System.out::println;
    PrintStream out = System.out;
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    Menu menu = new SimpleMenu();
    menu.add(Menu.ROOT, "Приготовить обед", action);
    menu.add(Menu.ROOT, "Сходить в магазин", action);
    menu.add(Menu.ROOT, "Погулять с собакой", action);
    menu.add("Приготовить обед", "Сварить суп", action);
    menu.add("Погулять с собакой", "Захватить мусор", action);
    menu.add("Захватить мусор", "взять коробку", action);
    System.setOut(new PrintStream(outputStream));
    Printer printer = new Printer();
    printer.print(menu);
    String sl = System.lineSeparator();
    String exp = "--1.Приготовить обед" + sl
               + "----1.1.Сварить суп" + sl
               + "--2.Сходить в магазин" + sl
               + "--3.Погулять с собакой" + sl
               + "----3.1.Захватить мусор" + sl
               + "------3.1.1.взять коробку";
    System.setOut(out);
    assertThat(outputStream.toString().trim()).isEqualTo(exp);
}
}