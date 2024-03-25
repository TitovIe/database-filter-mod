package ru.otus.databasefiltermod.expression.specification.boolean_;

import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Component;
import ru.otus.databasefiltermod.enums.OperatorType;
import ru.otus.databasefiltermod.expression.specification.FilterFunctionContext;

import java.sql.Date;

@Component
public class FilterFunctionBooleanNotEquals extends AbstractFilterFunctionBoolean {
    @Override
    public Predicate toPredicate(FilterFunctionContext filterFunctionContext) {
        return filterFunctionContext.getCriteriaBuilder()
                .notEqual(filterFunctionContext.getRoot().<Date>
                        get(filterFunctionContext.getColumnName()), Boolean.parseBoolean(filterFunctionContext.getValue()));
    }

    @Override
    public OperatorType getOperatorType() {
        return OperatorType.NOT_EQUALS;
    }
}
