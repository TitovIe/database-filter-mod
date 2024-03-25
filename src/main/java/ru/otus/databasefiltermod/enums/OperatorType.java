package ru.otus.databasefiltermod.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.NoSuchElementException;

@Getter
@RequiredArgsConstructor
public enum OperatorType {
    OR("or"),
    AND("and"),
    EQUALS("equals"),
    NOT_EQUALS("notEquals"),
    CONTAINS("contains"),
    NOT_CONTAINS("notContains"),
    CONTAINS_AND("containsAnd"),
    NOT_CONTAINS_AND("notContainsAnd"),
    CONTAINS_OR("containsOr"),
    NOT_CONTAINS_OR("notContainsOr"),
    EMPTY("empty"),
    NOT_EMPTY("notEmpty"),
    GREATER_THAN("greaterThan"),
    LESS_THAN("lessThan"),
    GREATER_THAN_OR_EQUALS_TO("greaterThanOrEqualsTo"),
    LESS_THAN_OR_EQUALS_TO("lessThanOrEqualsTo"),
    IN("in"),
    NOT_IN("notIn"),
    BETWEEN("between"),
    ;

    private final String name;

    public static OperatorType findByName(String name) {
        return Arrays.stream(OperatorType.values()).filter(operatorType -> operatorType.getName().equals(name)).findFirst().orElseThrow(() -> new NoSuchElementException(name));
    }
}
