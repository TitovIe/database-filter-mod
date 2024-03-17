package ru.otus.databasefiltermod.expression.specification.date;

import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Component;
import ru.otus.databasefiltermod.enums.OperatorType;
import ru.otus.databasefiltermod.expression.specification.FilterFunctionContext;

import java.sql.Date;
import java.time.OffsetDateTime;

@Component
public class FilterFunctionDateNotEquals extends AbstractFilterFunctionDate {
    @Override
    public Predicate toPredicate(FilterFunctionContext filterFunctionContext) {
        return filterFunctionContext.getCriteriaBuilder()
                .notEqual(filterFunctionContext.getRoot().<Date>get(filterFunctionContext.getColumnName()),
                        OffsetDateTime.parse(filterFunctionContext.getValue()));
    }

    @Override
    public OperatorType getOperatorType() {
        return OperatorType.NOT_EQUALS;
    }
}
