package ru.otus.databasefiltermod.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TargetType {
    FILTER("filter"),
    PROPERTY("property"),
    FIELD("field"),
    FUNCTION("function");

    private final String name;
}
