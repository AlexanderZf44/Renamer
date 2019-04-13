package ru.apermyakov.ranamer.input;

public interface Input {

    /**
     * Method for asking user and get result.
     */
    String ask(String question);

    /**
     * Метод для отрисовки информационных сообщений
     */
    void drow(String text);
}
