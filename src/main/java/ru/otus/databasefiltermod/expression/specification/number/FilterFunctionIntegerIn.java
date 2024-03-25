package ru.otus.databasefiltermod.expression.specification.number;

import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Component;
import ru.otus.databasefiltermod.enums.OperatorType;
import ru.otus.databasefiltermod.expression.specification.FilterFunctionContext;

import java.util.List;

@Component
public class FilterFunctionIntegerIn extends AbstractFilterFunctionInteger {
    @Override
    public Predicate toPredicate(FilterFunctionContext filterFunctionContext) {
        List<Long> values = getCastedValues(filterFunctionContext.getValue(), Long::parseLong);
        return filterFunctionContext.getRoot().get(filterFunctionContext.getColumnName()).in(values);
    }

    @Override
    public OperatorType getOperatorType() {
        return OperatorType.IN;
    }
}
