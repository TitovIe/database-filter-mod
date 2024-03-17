package ru.otus.databasefiltermod.expression.specification.array;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Component;
import ru.otus.databasefiltermod.enums.OperatorType;
import ru.otus.databasefiltermod.expression.specification.FilterFunctionContext;

@Component
public class FilterFunctionArrayNotEmpty extends AbstractFilterFunctionArray {
    @Override
    public Predicate toPredicate(FilterFunctionContext filterFunctionContext) {
        Path<Object> path = filterFunctionContext.getRoot().get(filterFunctionContext.getColumnName());
        CriteriaBuilder criteriaBuilder = filterFunctionContext.getCriteriaBuilder();
        return criteriaBuilder.isNull(criteriaBuilder.function("array_lenght", Integer.class, path, criteriaBuilder.literal(1))).not();
    }

    @Override
    public OperatorType getOperatorType() {
        return OperatorType.NOT_EMPTY;
    }
}
