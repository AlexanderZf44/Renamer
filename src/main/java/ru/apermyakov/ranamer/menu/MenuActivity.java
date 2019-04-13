package ru.apermyakov.ranamer.menu;

import ru.apermyakov.ranamer.input.ConsoleInput;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity {

    /**
     * Initial input info.
     */
    private ConsoleInput input;

    /**
     * Initial menu.
     */
    private List<String> actions = new ArrayList<>();

    /**
     * Initial menu.
     */
    private List<String> parameters;

    /**
     * Design menu tracker.
     *
     * @param input user input
     */
    public MenuActivity(ConsoleInput input, List<String> parameters) {
        this.input = input;
        this.parameters = parameters;
    }

    public void initial() {
        this.actions.add("Введи папку, в которой необходимо переименовать файлы, например, \"C:\\Users\\APermyakov\" : ");
        this.actions.add("Введи префикс нового имени, например,\"DCS_\" : ");
        this.actions.add("Введи итерацию нового имени без нолей, например, \"10\" : ");
        this.actions.add("Введи тип файлов, который нужно заменить с точкой, например, \".jpg\" : ");
    }

    public void greeting() {
        input.drow("Привет, эта программка поможет тебе переименовать кучу файлов разом!");
    }

    public void parting() {
        input.drow("Переименование файлов закончено, рад был помочь");
    }

    public void mainActivity() {
        this.actions.forEach(act -> parameters.add(input.ask(act)));
    }
}
