package ru.apermyakov.ranamer.validate;

import java.io.File;
import java.util.List;

public interface Validator {

    /**
     * Валидация типа файла
     */
    void validateFileType(File file, String type, List<String> errors);

    /**
     * Валидация директории
     */
    void validateDirectory(File[] suitableFiles, List<String> errors);
}
