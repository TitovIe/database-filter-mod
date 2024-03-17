package ru.otus.databasefiltermod.expression.specification.field;

import jakarta.persistence.criteria.Subquery;
import ru.otus.databasefiltermod.dto.Filter;
import ru.otus.databasefiltermod.enums.FieldType;
import ru.otus.databasefiltermod.expression.FilterContext;
import ru.otus.databasefiltermod.expression.FilterPropertyExpression;

public interface FieldFunction {
    <T, J> Subquery<J> process(Class<T> parentColumnType,
                               Class<J> childColumnType,
                               FilterContext parentFilterContext,
                               Filter filter,
                               FilterPropertyExpression filterPropertyExpression);

    FieldType getFieldType();
}
