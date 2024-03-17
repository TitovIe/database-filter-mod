package ru.otus.databasefiltermod.expression.specification.array;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Component;
import ru.otus.databasefiltermod.enums.OperatorType;
import ru.otus.databasefiltermod.expression.specification.FilterFunctionContext;

import java.util.function.Function;

@Component
public class FilterFunctionArrayNotContains extends AbstractFilterFunctionArray {
    @Override
    public Predicate toPredicate(FilterFunctionContext filterFunctionContext) {
        Path<Object> path = filterFunctionContext.getRoot().get(filterFunctionContext.getColumnName());
        CriteriaBuilder criteriaBuilder = filterFunctionContext.getCriteriaBuilder();
        String value = filterFunctionContext.getValue();
        Expression<?>[] literalExpressions = getLiteralExpressions(value, criteriaBuilder);
        Function<Expression<?>, Predicate> negativePredicateFunction = (expression) -> criteriaBuilder.notEqual(expression, criteriaBuilder.function("all", String.class, path));
        return criteriaBuilder.and(getPredicates(literalExpressions, negativePredicateFunction));
    }

    @Override
    public OperatorType getOperatorType() {
        return OperatorType.NOT_CONTAINS;
    }
}
