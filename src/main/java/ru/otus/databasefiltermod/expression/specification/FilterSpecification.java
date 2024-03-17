package ru.otus.databasefiltermod.expression.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor
public class FilterSpecification<T> implements Specification<T> {
    private final FilterFunctionFactory filterFunctionFactory;

    public Predicate toPredicate(FilterFunctionContext filterFunctionContext) {
        return filterFunctionFactory.getFilterFunctionColumnTypeFactory(filterFunctionContext.getColumnType())
                .getFilterFunction(filterFunctionContext.getOperatorType())
                .toPredicate(filterFunctionContext);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return null;
    }
}
