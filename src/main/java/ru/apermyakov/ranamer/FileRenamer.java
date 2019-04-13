package ru.apermyakov.ranamer;

import java.io.File;
import java.util.List;

public interface FileRenamer {

    /**
     * Инициализация переименовывания
     */
    void initRename();

    /**
     * Парсинг параметров из консоли
     */
    void parseParams(List<String> parameters);

    /**
     * Метод переименовывания файлов в директории
     *
     * @param directory directory
     */
    void renameFilesIntoDir(File directory, boolean isTemplate);
}
