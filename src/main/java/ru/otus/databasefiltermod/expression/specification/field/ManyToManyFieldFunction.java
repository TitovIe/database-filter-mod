package ru.otus.databasefiltermod.expression.specification.field;

import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;
import ru.otus.databasefiltermod.dto.Filter;
import ru.otus.databasefiltermod.enums.FieldType;
import ru.otus.databasefiltermod.expression.FilterContext;
import ru.otus.databasefiltermod.expression.FilterPropertyExpression;
import ru.otus.databasefiltermod.expression.specification.FilterSpecification;
import ru.otus.databasefiltermod.util.PostgresUtil;

@Component
@RequiredArgsConstructor
public class ManyToManyFieldFunction implements FieldFunction {
    private final ApplicationContext context;

    @Override
    public <T, J> Subquery<J> process(Class<T> parentColumnType,
                                      Class<J> childColumnType,
                                      FilterContext parentFilterContext,
                                      Filter filter,
                                      FilterPropertyExpression filterPropertyExpression) {
        CriteriaBuilder criteriaBuilder = parentFilterContext.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = (CriteriaQuery<T>) parentFilterContext.getCriteriaQuery();
        Root<T> root = (Root<T>) parentFilterContext.getRoot();
        Subquery<J> subquery = criteriaQuery.subquery(childColumnType);
        Root<T> subRoot = subquery.correlate(root);
        Join<T, J> join = subRoot.join((String) filter.getArgs().get("parentColumnName"));

        FilterSpecification<?> filterSpecification =
                (FilterSpecification<?>) context.getBeanProvider(
                        ResolvableType.forClassWithGenerics(FilterSpecification.class, parentColumnType)).getObject();

        FilterContext childFilterContext = FilterContext.builder()
                .filterSpecification(filterSpecification)
                .criteriaBuilder(criteriaBuilder)
                .criteriaQuery(criteriaQuery)
                .root(join)
                .classType(parentColumnType)
                .fields(PostgresUtil.getFieldsByClass(childColumnType))
                .build();

        filterPropertyExpression.setFilter(filter);
        Predicate[] predicates = filterPropertyExpression.interpret(childFilterContext);

        return subquery.select(join).where(predicates);
    }

    @Override
    public FieldType getFieldType() {
        return FieldType.MANY_TO_MANY;
    }
}
