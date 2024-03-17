package ru.otus.databasefiltermod.expression.specification.date;

import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Component;
import ru.otus.databasefiltermod.enums.OperatorType;
import ru.otus.databasefiltermod.expression.specification.FilterFunctionContext;

import java.time.OffsetDateTime;
import java.util.List;

@Component
public class FilterFunctionDateIn extends AbstractFilterFunctionDate {
    @Override
    public Predicate toPredicate(FilterFunctionContext filterFunctionContext) {
        List<OffsetDateTime> dateTimes = getCastedValues(filterFunctionContext.getValue(), OffsetDateTime::parse);
        return filterFunctionContext.getRoot().in(dateTimes);
    }

    @Override
    public OperatorType getOperatorType() {
        return OperatorType.IN;
    }
}
