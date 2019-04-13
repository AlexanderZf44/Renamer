package ru.apermyakov.ranamer.validate;

import java.io.File;
import java.util.List;

public class FileValidator implements Validator {

    /**
     * Валидация типа файла
     */
    public void validateFileType(File file, String type, List<String> errors) {
        String fileName = file.getName();
        String fileType = fileName.substring(fileName.indexOf(".")).toLowerCase();
        if (!fileType.equals(type.toLowerCase())) {
            errors.add(String.format("Файл %s имеет тип %s, отличный от указанного %s", fileName, fileType, type));
        }
    }

    /**
     * Валидация директории
     */
    public void validateDirectory(File[] suitableFiles, List<String> errors) {
        if (suitableFiles == null) {
            errors.add("Директория не является папкой");
            return;
        }
        if (suitableFiles.length == 0) {
            errors.add("Директория является пустой и не содержит нужных файлов");
        }
    }

}
