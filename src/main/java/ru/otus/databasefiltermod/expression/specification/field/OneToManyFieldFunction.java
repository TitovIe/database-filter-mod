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

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class OneToManyFieldFunction implements FieldFunction {
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
        Root<J> subRoot = subquery.from(childColumnType);

        FilterSpecification<?> filterSpecification =
                (FilterSpecification<?>) context.getBeanProvider(
                        ResolvableType.forClassWithGenerics(FilterSpecification.class, parentColumnType)).getObject();

        FilterContext childFilterContext = FilterContext.builder()
                .filterSpecification(filterSpecification)
                .criteriaBuilder(criteriaBuilder)
                .criteriaQuery(criteriaQuery)
                .root(subRoot)
                .classType(parentColumnType)
                .fields(PostgresUtil.getFieldsByClass(childColumnType))
                .build();

        filterPropertyExpression.setFilter(filter);
        Predicate[] predicates = filterPropertyExpression.interpret(childFilterContext);

        String link;
        try {
            link = Arrays.stream(childColumnType.getDeclaredFields())
                    .filter(field ->
                            field.getType().equals(parentColumnType)
                                    && !Objects.isNull(field.getAnnotation(FieldType.MANY_TO_ONE.getAnnotationClassType())))
                    .map(Field::getName)
                    .findFirst()
                    .orElseThrow(NoSuchFieldException::new);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        return subquery.select(subRoot).where(
                Stream.concat(Arrays.stream(predicates),
                                Arrays.stream(new Predicate[]{criteriaBuilder.equal(root.get("id"), subRoot.get(link).get("id"))}))
                        .toArray(Predicate[]::new));
    }

    @Override
    public FieldType getFieldType() {
        return FieldType.ONE_TO_MANY;
    }
}