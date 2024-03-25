package ru.otus.databasefiltermod.parser;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.otus.databasefiltermod.dto.Filter;
import ru.otus.databasefiltermod.enums.TargetType;
import ru.otus.databasefiltermod.expression.FilterExpression;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class FilterTargetParser implements TargetParser {
    private final OperatorParserFactory operatorParserFactory;

    @Override
    public FilterExpression parse(Filter filter) {
        return operatorParserFactory.getOperatorParser((String) filter.getArgs().get("operator")).parse(filter);
    }

    @Override
    public String getTargetTypeName() {
        return TargetType.FILTER.getName();
    }
}
