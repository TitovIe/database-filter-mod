package ru.otus.databasefiltermod.expression;

import jakarta.persistence.criteria.Predicate;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.otus.databasefiltermod.dto.Filter;
import ru.otus.databasefiltermod.enums.ColumnType;
import ru.otus.databasefiltermod.enums.DatabaseColumn;
import ru.otus.databasefiltermod.enums.OperatorType;
import ru.otus.databasefiltermod.expression.specification.FilterFunctionContext;
import ru.otus.databasefiltermod.expression.specification.FilterSpecification;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Setter
@Component
@Scope(SCOPE_PROTOTYPE)
public class FilterPropertyExpression implements FilterExpression {
    private Filter filter;

    @Override
    public Predicate[] interpret(FilterContext filterContext) {
        String columnName = (String) filter.getArgs().get("columnName");
        ColumnType columnType = DatabaseColumn.findByName(columnName).getColumnType();
        String operator = (String) filter.getArgs().get("operator");
        OperatorType operatorType = OperatorType.findByName(operator);
        String value = (String) filter.getArgs().get("value");

        FilterSpecification<?> filterSpecification = filterContext.getFilterSpecification();

        return new Predicate[]{filterSpecification.toPredicate(FilterFunctionContext.builder()
                .columnName(columnName)
                .value(value)
                .columnType(columnType)
                .operatorType(operatorType)
                .root(filterContext.getRoot())
                .criteriaQuery(filterContext.getCriteriaQuery())
                .criteriaBuilder(filterContext.getCriteriaBuilder())
                .build())};
    }
}
