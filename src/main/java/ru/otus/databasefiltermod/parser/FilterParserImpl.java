package ru.otus.databasefiltermod.parser;

import org.springframework.stereotype.Component;
import ru.otus.databasefiltermod.dto.Filter;
import ru.otus.databasefiltermod.expression.*;

import java.util.stream.Collectors;

@Component
public class FilterParserImpl implements FilterParser {
    public FilterExpression parse(Filter filter) {
        switch (filter.getTarget()) {
            case "filter" -> {
                switch ((String) filter.getArgs().get("operator")) {
                    case "or" -> {
                        FilterOrExpression filterOrExpression = new FilterOrExpression();
                        filterOrExpression.setFilterExpressions(
                                filter.getFilters()
                                        .stream()
                                        .map(this::parse)
                                        .collect(Collectors.toList()));
                        return filterOrExpression;
                    }
                    case "and" -> {
                        FilterAndExpression filterAndExpression = new FilterAndExpression();
                        filterAndExpression.setFilterExpressions(
                                filter.getFilters()
                                        .stream()
                                        .map(this::parse)
                                        .collect(Collectors.toList()));
                        return filterAndExpression;
                    }
                }
            }
            case "property" -> {
                return new FilterPropertyExpression();
            }
            case "field" -> {
                return new FilterFieldExpression(null, null);
            }
            case "function" -> {
                return new FilterFunctionExpression();
            }
            default -> throw new RuntimeException();
        }
        return null;
    }
}
