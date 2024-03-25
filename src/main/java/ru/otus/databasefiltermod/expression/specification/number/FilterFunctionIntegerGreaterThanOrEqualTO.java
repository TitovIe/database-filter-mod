package ru.otus.databasefiltermod.expression.specification.number;

import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Component;
import ru.otus.databasefiltermod.enums.OperatorType;
import ru.otus.databasefiltermod.expression.specification.FilterFunctionContext;

@Component
public class FilterFunctionIntegerGreaterThanOrEqualTO extends AbstractFilterFunctionInteger {
    @Override
    public Predicate toPredicate(FilterFunctionContext filterFunctionContext) {
        return filterFunctionContext.getCriteriaBuilder()
                .greaterThanOrEqualTo(filterFunctionContext.getRoot().get(filterFunctionContext.getColumnName()),
                        Long.parseLong(filterFunctionContext.getValue()));
    }

    @Override
    public OperatorType getOperatorType() {
        return OperatorType.GREATER_THAN_OR_EQUALS_TO;
    }
}
