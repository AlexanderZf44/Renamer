package ru.apermyakov.ranamer.input;

import java.util.Scanner;

public class ConsoleInput implements Input {

    /**
     * Сканер консоли
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Method for asking user and get result.
     */
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    /**
     * Метод для отрисовки информационных сообщений
     */
    @Override
    public void drow(String text) {
        System.out.println(text);
    }
}
