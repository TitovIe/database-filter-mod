package ru.otus.databasefiltermod.expression;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Setter
@Component
@Scope(SCOPE_PROTOTYPE)
public class FilterAndExpression implements FilterExpression {
    private List<FilterExpression> filterExpressions;

    @Override
    public Predicate[] interpret(FilterContext filterContext) {
        CriteriaBuilder criteriaBuilder = filterContext.getCriteriaBuilder();
        return new Predicate[]{criteriaBuilder.and(
                filterExpressions.stream()
                        .flatMap(filterExpression -> Arrays.stream(filterExpression.interpret(filterContext)))
                        .toArray(Predicate[]::new))};
    }
}
