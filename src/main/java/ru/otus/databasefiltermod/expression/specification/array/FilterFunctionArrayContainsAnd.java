package ru.otus.databasefiltermod.expression.specification.array;

import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.databasefiltermod.enums.OperatorType;
import ru.otus.databasefiltermod.expression.specification.FilterFunction;
import ru.otus.databasefiltermod.expression.specification.FilterFunctionContext;

@Component
@RequiredArgsConstructor
public class FilterFunctionArrayContainsAnd extends AbstractFilterFunctionArray {
    private final FilterFunction filterFunctionArrayContains;

    @Override
    public Predicate toPredicate(FilterFunctionContext filterFunctionContext) {
        return filterFunctionArrayContains.toPredicate(filterFunctionContext);
    }

    @Override
    public OperatorType getOperatorType() {
        return OperatorType.CONTAINS_AND;
    }
}
