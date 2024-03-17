package ru.otus.databasefiltermod.expression.specification.boolean_;

import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Component;
import ru.otus.databasefiltermod.enums.OperatorType;
import ru.otus.databasefiltermod.expression.specification.FilterFunctionContext;

@Component
public class FilterFunctionBooleanNotEmpty extends AbstractFilterFunctionBoolean {
    @Override
    public Predicate toPredicate(FilterFunctionContext filterFunctionContext) {
        return filterFunctionContext.getCriteriaBuilder()
                .isNotNull((filterFunctionContext.getRoot()
                        .get(filterFunctionContext.getColumnName())));
    }

    @Override
    public OperatorType getOperatorType() {
        return OperatorType.NOT_EMPTY;
    }
}
