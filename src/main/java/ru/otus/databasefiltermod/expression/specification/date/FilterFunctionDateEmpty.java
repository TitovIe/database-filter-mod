package ru.otus.databasefiltermod.expression.specification.date;

import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Component;
import ru.otus.databasefiltermod.enums.OperatorType;
import ru.otus.databasefiltermod.expression.specification.FilterFunctionContext;

@Component
public class FilterFunctionDateEmpty extends AbstractFilterFunctionDate {
    @Override
    public Predicate toPredicate(FilterFunctionContext filterFunctionContext) {
        return filterFunctionContext.getCriteriaBuilder()
                .isNull((filterFunctionContext.getRoot()
                        .get(filterFunctionContext.getColumnName())));
    }

    @Override
    public OperatorType getOperatorType() {
        return OperatorType.EMPTY;
    }
}
