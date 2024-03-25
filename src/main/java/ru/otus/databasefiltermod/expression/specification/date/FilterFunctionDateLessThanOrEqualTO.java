package ru.otus.databasefiltermod.expression.specification.date;

import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Component;
import ru.otus.databasefiltermod.enums.OperatorType;
import ru.otus.databasefiltermod.expression.specification.FilterFunctionContext;

import java.time.OffsetDateTime;

@Component
public class FilterFunctionDateLessThanOrEqualTO extends AbstractFilterFunctionDate {
    @Override
    public Predicate toPredicate(FilterFunctionContext filterFunctionContext) {
        return filterFunctionContext.getCriteriaBuilder()
                .lessThanOrEqualTo(filterFunctionContext.getRoot().get(filterFunctionContext.getColumnName()),
                        OffsetDateTime.parse(filterFunctionContext.getValue()));
    }

    @Override
    public OperatorType getOperatorType() {
        return OperatorType.LESS_THAN_OR_EQUALS_TO;
    }
}
