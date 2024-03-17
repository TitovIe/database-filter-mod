package ru.otus.databasefiltermod.parser;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.otus.databasefiltermod.dto.Filter;
import ru.otus.databasefiltermod.enums.TargetType;
import ru.otus.databasefiltermod.expression.FilterExpression;
import ru.otus.databasefiltermod.expression.FilterFieldExpression;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class FieldTargetParser implements TargetParser {
    private final FilterFieldExpression filterFieldExpression;

    @Override
    public FilterExpression parse(Filter filter) {
        filterFieldExpression.setFilter(filter);
        return filterFieldExpression;
    }

    @Override
    public String getTargetTypeName() {
        return TargetType.FIELD.getName();
    }
}
