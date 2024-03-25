package ru.otus.databasefiltermod.expression.specification.number;

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
public class FilterFunctionIntegerFactory implements FilterFunctionColumnTypeFactory {
    private final Map<OperatorType, AbstractFilterFunctionInteger> operatorTypeToFilterFunctionInteger;

    public FilterFunctionIntegerFactory(List<AbstractFilterFunctionInteger> operatorTypeToFilterFunctionInteger) {
        this.operatorTypeToFilterFunctionInteger = operatorTypeToFilterFunctionInteger.stream()
                .collect(Collectors.toMap(AbstractFilterFunctionInteger::getOperatorType, Function.identity()));
    }

    @Override
    public FilterFunction getFilterFunction(OperatorType operatorType) {
        return operatorTypeToFilterFunctionInteger.get(operatorType);
    }

    @Override
    public ColumnType getColumnType() {
        return ColumnType.INTEGER;
    }
}
