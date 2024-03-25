package ru.otus.databasefiltermod.expression.specification.date;

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
public class FilterFunctionDateFactory implements FilterFunctionColumnTypeFactory {
    private final Map<OperatorType, AbstractFilterFunctionDate> operatorTypeToFilterFunctionDate;

    public FilterFunctionDateFactory(List<AbstractFilterFunctionDate> operatorTypeToFilterFunctionDate) {
        this.operatorTypeToFilterFunctionDate = operatorTypeToFilterFunctionDate.stream()
                .collect(Collectors.toMap(AbstractFilterFunctionDate::getOperatorType, Function.identity()));
    }

    @Override
    public FilterFunction getFilterFunction(OperatorType operatorType) {
        return operatorTypeToFilterFunctionDate.get(operatorType);
    }

    @Override
    public ColumnType getColumnType() {
        return ColumnType.DATE;
    }
}
