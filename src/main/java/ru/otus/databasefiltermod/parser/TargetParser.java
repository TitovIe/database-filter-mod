package ru.otus.databasefiltermod.parser;

import ru.otus.databasefiltermod.dto.Filter;
import ru.otus.databasefiltermod.expression.FilterExpression;

public interface TargetParser {
    FilterExpression parse(Filter filter);
    String getTargetTypeName();
}
