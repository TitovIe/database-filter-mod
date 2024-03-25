package ru.otus.databasefiltermod.expression.specification.number;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.databasefiltermod.enums.OperatorType;
import ru.otus.databasefiltermod.expression.specification.FilterFunction;
import ru.otus.databasefiltermod.expression.specification.FilterFunctionContext;

@Component
@RequiredArgsConstructor
public class FilterFunctionIntegerNotIn extends AbstractFilterFunctionInteger {
    private final FilterFunction filterFunctionIntegerIn;

    @Override
    public Predicate toPredicate(FilterFunctionContext filterFunctionContext) {
        return filterFunctionIntegerIn.toPredicate(filterFunctionContext).not();
    }

    @Override
    public OperatorType getOperatorType() {
        return OperatorType.NOT_IN;
    }
}
