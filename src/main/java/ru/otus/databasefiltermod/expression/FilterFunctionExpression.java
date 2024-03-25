package ru.otus.databasefiltermod.expression;

import jakarta.persistence.criteria.Predicate;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.otus.databasefiltermod.dto.Filter;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Setter
@Component
@Scope(SCOPE_PROTOTYPE)
public class FilterFunctionExpression implements FilterExpression {
    private Filter filter;

    @Override
    public Predicate[] interpret(FilterContext filterContext) {
        return null;
    }
}
