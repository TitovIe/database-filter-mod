package ru.otus.databasefiltermod.expression.specification;

import ru.otus.databasefiltermod.enums.ColumnType;

public interface FilterFunctionFactory {
    FilterFunctionColumnTypeFactory getFilterFunctionColumnTypeFactory(ColumnType columnType);
}
