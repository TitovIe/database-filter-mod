package ru.otus.databasefiltermod.expression.specification.date;

import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Component;
import ru.otus.databasefiltermod.enums.OperatorType;
import ru.otus.databasefiltermod.expression.specification.FilterFunctionContext;

import java.time.OffsetDateTime;
import java.util.List;

@Component
public class FilterFunctionDateBetween extends AbstractFilterFunctionDate {
    @Override
    public Predicate toPredicate(FilterFunctionContext filterFunctionContext) {
        List<OffsetDateTime> dateTimes = getCastedValues(filterFunctionContext.getValue(), OffsetDateTime::parse);
        return filterFunctionContext.getCriteriaBuilder().between(
                filterFunctionContext.getRoot().get(
                        filterFunctionContext.getColumnName()), dateTimes.get(0), dateTimes.get(1));
    }

    @Override
    public OperatorType getOperatorType() {
        return OperatorType.BETWEEN;
    }
}
