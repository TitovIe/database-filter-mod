package ru.otus.databasefiltermod.expression.specification.boolean_;

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
public class FilterFunctionBooleanFactory implements FilterFunctionColumnTypeFactory {
    private final Map<OperatorType, AbstractFilterFunctionBoolean> operatorTypeToFilterFunctionBoolean;

    public FilterFunctionBooleanFactory(List<AbstractFilterFunctionBoolean> operatorTypeToFilterFunctionBoolean) {
        this.operatorTypeToFilterFunctionBoolean = operatorTypeToFilterFunctionBoolean.stream()
                .collect(Collectors.toMap(AbstractFilterFunctionBoolean::getOperatorType, Function.identity()));
    }

    @Override
    public FilterFunction getFilterFunction(OperatorType operatorType) {
        return operatorTypeToFilterFunctionBoolean.get(operatorType);
    }

    @Override
    public ColumnType getColumnType() {
        return ColumnType.BOOLEAN;
    }
}
