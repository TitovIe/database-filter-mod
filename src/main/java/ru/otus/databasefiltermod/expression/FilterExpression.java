package ru.otus.databasefiltermod.expression;

import jakarta.persistence.criteria.Predicate;

public interface FilterExpression {
    Predicate[] interpret(FilterContext filterContext);
}
