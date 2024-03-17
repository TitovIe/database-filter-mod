package ru.otus.databasefiltermod.expression.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public abstract class AbstractFilterFunction implements FilterFunction {
    protected <T> List<T> getCastedValues(String value, Function<String, T> function) {
        return Arrays.stream(value.split(",")).map(function).toList();
    }

    protected Expression<?>[] getLiteralExpressions(String value, CriteriaBuilder builder) {
        return Arrays.stream(value.split(",")).map(builder::literal).toArray(Expression<?>[]::new);
    }

    protected Predicate[] getPredicates(Expression<?>[] expressions, Function<Expression<?>, Predicate> function) {
        return Arrays.stream(expressions).map(function).toArray(Predicate[]::new);
    }
}
