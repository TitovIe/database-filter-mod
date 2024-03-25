package ru.otus.databasefiltermod.parser;

import ru.otus.databasefiltermod.dto.Filter;
import ru.otus.databasefiltermod.expression.FilterExpression;

public interface OperatorParser {
    FilterExpression parse(Filter filter);
    String getOperatorTypeName();
}
