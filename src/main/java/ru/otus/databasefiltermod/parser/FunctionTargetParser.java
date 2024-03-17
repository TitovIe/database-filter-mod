package ru.otus.databasefiltermod.parser;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.otus.databasefiltermod.dto.Filter;
import ru.otus.databasefiltermod.enums.TargetType;
import ru.otus.databasefiltermod.expression.FilterExpression;
import ru.otus.databasefiltermod.expression.FilterFunctionExpression;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class FunctionTargetParser implements TargetParser {
    private final FilterFunctionExpression filterFunctionExpression;

    @Override
    public FilterExpression parse(Filter filter) {
        filterFunctionExpression.setFilter(filter);
        return filterFunctionExpression;
    }

    @Override
    public String getTargetTypeName() {
        return TargetType.FUNCTION.getName();
    }
}
