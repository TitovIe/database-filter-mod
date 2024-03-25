package ru.otus.databasefiltermod.expression.specification;

import jakarta.persistence.criteria.Predicate;
import ru.otus.databasefiltermod.enums.OperatorType;

public interface FilterFunction {
    Predicate toPredicate(FilterFunctionContext filterFunctionContext);
    OperatorType getOperatorType();
}
