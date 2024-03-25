package ru.otus.databasefiltermod.expression.specification;

import org.springframework.stereotype.Component;
import ru.otus.databasefiltermod.enums.ColumnType;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class FilterFunctionFactoryImpl implements FilterFunctionFactory {
    private final Map<ColumnType, FilterFunctionColumnTypeFactory> columnTypeToFilterFunctionColumnTypeFactory;

    public FilterFunctionFactoryImpl(List<FilterFunctionColumnTypeFactory> columnTypeToFilterFunctionColumnTypeFactory) {
        this.columnTypeToFilterFunctionColumnTypeFactory = columnTypeToFilterFunctionColumnTypeFactory.stream()
                .collect(Collectors.toMap(FilterFunctionColumnTypeFactory::getColumnType, Function.identity()));
    }

    @Override
    public FilterFunctionColumnTypeFactory getFilterFunctionColumnTypeFactory(ColumnType columnType) {
        return columnTypeToFilterFunctionColumnTypeFactory.get(columnType);
    }
}
