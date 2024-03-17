package ru.otus.databasefiltermod.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.NoSuchElementException;

@Getter
@RequiredArgsConstructor
public enum DatabaseColumn {
    FIRST_NAME("firstName", ColumnType.STRING),
    LAST_NAME("lastName", ColumnType.STRING),
    HIRE_DATE("hireDate", ColumnType.DATE),
    ALIASES("aliases", ColumnType.ARRAY),
    DETAILS("details", ColumnType.JSON),
    PRIORITY("priority", ColumnType.INTEGER),
    TYPE("type", ColumnType.STRING),
    ACTIVE("active", ColumnType.BOOLEAN),
    COMPETENCIES("competencies", ColumnType.ARRAY),
    ;

    private final String name;
    private final ColumnType columnType;

    public static DatabaseColumn findByName(String name) {
        return Arrays.stream(DatabaseColumn.values()).filter(databaseColumn -> databaseColumn.getName().equals(name)).findFirst().orElseThrow(() -> new NoSuchElementException(name));
    }
}
