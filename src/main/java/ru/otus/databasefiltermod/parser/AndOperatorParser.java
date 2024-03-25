package ru.otus.databasefiltermod.parser;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.otus.databasefiltermod.dto.Filter;
import ru.otus.databasefiltermod.enums.OperatorType;
import ru.otus.databasefiltermod.expression.FilterAndExpression;
import ru.otus.databasefiltermod.expression.FilterExpression;

import java.util.stream.Collectors;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
public class AndOperatorParser implements OperatorParser {
    private final TargetParserFactory targetParserFactory;
    private final FilterAndExpression filterAndExpression;

    public AndOperatorParser(@Lazy TargetParserFactory targetParserFactory, FilterAndExpression filterAndExpression) {
        this.targetParserFactory = targetParserFactory;
        this.filterAndExpression = filterAndExpression;
    }

    @Override
    public FilterExpression parse(Filter filter) {
        filterAndExpression.setFilterExpressions(
                filter.getFilters()
                        .stream()
                        .map(filter1 -> targetParserFactory.getTargetParser(filter1.getTarget()).parse(filter1))
                        .collect(Collectors.toList()));
        return filterAndExpression;
    }

    @Override
    public String getOperatorTypeName() {
        return OperatorType.AND.getName();
    }
}
