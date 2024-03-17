package ru.otus.databasefiltermod.expression.specification.array;

import org.springframework.stereotype.Component;
import ru.otus.databasefiltermod.enums.ColumnType;
import ru.otus.databasefiltermod.enums.OperatorType;
import ru.otus.databasefiltermod.expression.specification.FilterFunction;
import ru.otus.databasefiltermod.expression.specification.FilterFunctionColumnTypeFactory;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class FilterFunctionArrayFactory implements FilterFunctionColumnTypeFactory {
    private final Map<OperatorType, AbstractFilterFunctionArray> operatorTypeToFilterFunctionArray;

    public FilterFunctionArrayFactory(List<AbstractFilterFunctionArray> operatorTypeToFilterFunctionArray) {
        this.operatorTypeToFilterFunctionArray = operatorTypeToFilterFunctionArray.stream()
                .collect(Collectors.toMap(AbstractFilterFunctionArray::getOperatorType, Function.identity()));
    }

    @Override
    public FilterFunction getFilterFunction(OperatorType operatorType) {
        return operatorTypeToFilterFunctionArray.get(operatorType);
    }

    @Override
    public ColumnType getColumnType() {
        return ColumnType.ARRAY;
    }
}
