package ru.apermyakov.ranamer;

import ru.apermyakov.ranamer.input.ConsoleInput;
import ru.apermyakov.ranamer.menu.MenuActivity;
import ru.apermyakov.ranamer.validate.FileValidator;

import java.io.File;
import java.util.*;
import java.util.stream.Stream;

public class FileRenamerImpl implements FileRenamer {

    /**
     * Префикс названия
     */
    private String prefix;

    /**
     * Итерация имени
     */
    private int nameIteration;

    /**
     * Тип файла
     */
    private String fileType;

    /**
     * Валидатор файлов
     */
    private FileValidator validator = new FileValidator();

    /**
     * Директория с файлами
     */
    private String directory;

    /**
     * Итерированное имя
     */
    private String iterableName;

    /**
     * Инициализация переименовывания
     */
    @Override
    public void initRename() {
        int currentIteration = this.nameIteration;
        renameFilesIntoDir(new File(this.directory), true);
        this.nameIteration = currentIteration;
        this.iterableName = null;
        renameFilesIntoDir(new File(this.directory), false);
    }

    /**
     * Парсинг параметров из консоли
     */
    @Override
    public void parseParams(List<String> parameters) {
        this.directory = parameters.get(0);
        this.prefix = parameters.get(1);
        this.nameIteration = Integer.valueOf(parameters.get(2));
        this.fileType = parameters.get(3);
    }

    /**
     * Метод переименовывания файлов в директории
     *
     * @param directory directory
     */
    public void renameFilesIntoDir(File directory, boolean isTemplate) {
        File[] suitableFiles = directory.listFiles();
        List<String> errors = new ArrayList<>();

        validator.validateDirectory(suitableFiles, errors);

        if (!errors.isEmpty()) {
            errors.forEach(System.out::println);
            return;
        }

        System.out.println("Файлы найдены");
        Optional.ofNullable(suitableFiles)
                .map(Arrays::stream)
                .orElseGet(Stream::empty)
                .filter(File::isFile)
                .forEach(targetFile -> renameFile(targetFile, isTemplate ? "template" : this.prefix));
    }

    /**
     * Переименование файла
     */
    private void renameFile(File targetFile, String prefix) {
        List<String> errors = new ArrayList<>();

        this.validator.validateFileType(targetFile, this.fileType, errors);

        if (!errors.isEmpty()) {
            errors.forEach(System.out::println);
            return;
        }

        String lastName = targetFile.getName();
        String newName;
        if (this.iterableName == null) {
            newName = createName(prefix);
        } else {
            newName = iterateName(prefix);
        }

        File fileWithNewName = new File(String.join("\\", this.directory, newName));
        boolean renameResult = targetFile.renameTo(fileWithNewName);

        if (renameResult) {
            System.out.println(lastName + " -> " + newName);
            this.iterableName = newName;
        } else {
            System.out.println("Произошла ошибка");
        }
    }

    /**
     * Итерация имени
     */
    private String iterateName(String prefix) {
        ++this.nameIteration;
        return createName(prefix);
    }

    /**
     * Создание имени
     */
    private String createName(String prefix) {
        String iteration = String.format("%04d", this.nameIteration);
        String iterableName = String.join("", prefix, iteration);
        return String.join("", iterableName, this.fileType);
    }

    /**
     * Основной метод
     *
     * @param args args
     */
    public static void main(String[] args) {
        FileRenamer task = new FileRenamerImpl();
        ConsoleInput input = new ConsoleInput();
        List<String> parameters = new ArrayList<>();
        MenuActivity menu = new MenuActivity(input, parameters);
        menu.initial();
        menu.greeting();
        menu.mainActivity();
        task.parseParams(parameters);
        task.initRename();
        menu.parting();
    }
}
