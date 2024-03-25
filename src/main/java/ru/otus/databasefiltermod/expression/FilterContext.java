package ru.otus.databasefiltermod.expression;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.From;
import lombok.Builder;
import lombok.Data;
import ru.otus.databasefiltermod.expression.specification.FilterSpecification;

import java.util.Set;

@Data
@Builder
public class FilterContext {
    private FilterSpecification<?> filterSpecification;
    private CriteriaBuilder criteriaBuilder;
    private CriteriaQuery<?> criteriaQuery;
    private From<?, ?> root;
    private Class<?> classType;
    private Set<String> fields;
}
