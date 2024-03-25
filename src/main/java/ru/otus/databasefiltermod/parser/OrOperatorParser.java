package ru.otus.databasefiltermod.parser;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.otus.databasefiltermod.dto.Filter;
import ru.otus.databasefiltermod.enums.OperatorType;
import ru.otus.databasefiltermod.expression.FilterExpression;
import ru.otus.databasefiltermod.expression.FilterOrExpression;

import java.util.stream.Collectors;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
public class OrOperatorParser implements OperatorParser {
    private final TargetParserFactory targetParserFactory;
    private final FilterOrExpression filterOrExpression;

    public OrOperatorParser(@Lazy TargetParserFactory targetParserFactory, FilterOrExpression filterOrExpression) {
        this.targetParserFactory = targetParserFactory;
        this.filterOrExpression = filterOrExpression;
    }

    @Override
    public FilterExpression parse(Filter filter) {
        filterOrExpression.setFilterExpressions(
                filter.getFilters()
                        .stream()
                        .map(filter1 -> targetParserFactory.getTargetParser(filter1.getTarget()).parse(filter1))
                        .collect(Collectors.toList()));
        return filterOrExpression;
    }

    @Override
    public String getOperatorTypeName() {
        return OperatorType.OR.getName();
    }
}
