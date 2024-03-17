package ru.otus.databasefiltermod.parser;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.otus.databasefiltermod.dto.Filter;
import ru.otus.databasefiltermod.enums.TargetType;
import ru.otus.databasefiltermod.expression.FilterExpression;
import ru.otus.databasefiltermod.expression.FilterPropertyExpression;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class PropertyTargetParser implements TargetParser {
    private final FilterPropertyExpression filterPropertyExpression;

    @Override
    public FilterExpression parse(Filter filter) {
        filterPropertyExpression.setFilter(filter);
        return filterPropertyExpression;
    }

    @Override
    public String getTargetTypeName() {
        return TargetType.PROPERTY.getName();
    }
}
