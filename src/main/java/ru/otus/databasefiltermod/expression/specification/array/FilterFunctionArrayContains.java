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
public class FilterFunctionArrayContains extends AbstractFilterFunctionArray {
    @Override
    public Predicate toPredicate(FilterFunctionContext filterFunctionContext) {
        Path<String> path = filterFunctionContext.getRoot().get(filterFunctionContext.getColumnName());
        CriteriaBuilder criteriaBuilder = filterFunctionContext.getCriteriaBuilder();
        String value = filterFunctionContext.getValue();
        Expression<?>[] literalExpressions = getLiteralExpressions(value, criteriaBuilder);
        Function<Expression<?>, Predicate> positivePredicateFunction = (expression) -> criteriaBuilder.equal(expression, criteriaBuilder.function("some", String.class, path));
        return criteriaBuilder.and(getPredicates(literalExpressions, positivePredicateFunction));
    }

    @Override
    public OperatorType getOperatorType() {
        return OperatorType.CONTAINS;
    }
}
