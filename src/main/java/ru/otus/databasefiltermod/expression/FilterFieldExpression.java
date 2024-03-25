package ru.otus.databasefiltermod.expression;

import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.otus.databasefiltermod.dto.Filter;
import ru.otus.databasefiltermod.enums.FieldType;
import ru.otus.databasefiltermod.expression.specification.field.FieldFunction;
import ru.otus.databasefiltermod.expression.specification.field.FieldFunctionFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Setter
@Component
@Scope(SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class FilterFieldExpression implements FilterExpression {
    private Filter filter;
    private final FilterPropertyExpression filterPropertyExpression;
    private final FieldFunctionFactory fieldFunctionFactory;

    @SneakyThrows
    @Override
    public Predicate[] interpret(FilterContext parentFilterContext) {
        String parentColumnName = (String) filter.getArgs().get("parentColumnName");
        Class<?> parentColumnType = parentFilterContext.getClassType();

        Class<? extends Annotation> annotationClassType = Arrays.stream(parentColumnType.getDeclaredField(parentColumnName).getAnnotations())
                .map(Annotation::annotationType)
                .filter(annotationType -> FieldType.getAnnotationClassTypes().contains(annotationType))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        FieldFunction fieldFunction = fieldFunctionFactory.getFieldFunction(FieldType.findByAnnotationClassType(annotationClassType));
        Field childColumn = parentColumnType.getDeclaredField(parentColumnName);
        ParameterizedType stringListType = (ParameterizedType) childColumn.getGenericType();
        Class<?> childColumnType = (Class<?>) stringListType.getActualTypeArguments()[0];

        Subquery<?> subquery = fieldFunction.process(
                parentColumnType,
                childColumnType,
                parentFilterContext,
                filter,
                filterPropertyExpression);

        return new Predicate[]{parentFilterContext.getCriteriaBuilder().exists(subquery)};
    }
}
