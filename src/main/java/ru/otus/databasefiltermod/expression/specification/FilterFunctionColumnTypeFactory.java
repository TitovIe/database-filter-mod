package ru.otus.databasefiltermod.expression.specification;

import ru.otus.databasefiltermod.enums.ColumnType;
import ru.otus.databasefiltermod.enums.OperatorType;

public interface FilterFunctionColumnTypeFactory {
    FilterFunction getFilterFunction(OperatorType operatorType);

    ColumnType getColumnType();
}
