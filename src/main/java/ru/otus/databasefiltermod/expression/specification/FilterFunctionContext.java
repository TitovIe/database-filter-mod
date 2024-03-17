package ru.otus.databasefiltermod.expression.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.From;
import lombok.Builder;
import lombok.Data;
import ru.otus.databasefiltermod.enums.ColumnType;
import ru.otus.databasefiltermod.enums.OperatorType;

@Data
@Builder
public class FilterFunctionContext {
    private String columnName;
    private String value;
    private ColumnType columnType;
    private OperatorType operatorType;
    private From<?, ?> root;
    private CriteriaQuery<?> criteriaQuery;
    private CriteriaBuilder criteriaBuilder;
}
