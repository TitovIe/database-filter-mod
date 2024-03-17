package ru.otus.databasefiltermod.expression.specification.field;

import org.springframework.stereotype.Component;
import ru.otus.databasefiltermod.enums.FieldType;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class FieldFunctionFactory {
    private final Map<FieldType, FieldFunction> fieldTypeToFieldFunction;

    public FieldFunctionFactory(List<FieldFunction> fieldFunctions) {
        this.fieldTypeToFieldFunction = fieldFunctions.stream()
                .collect(Collectors.toMap(FieldFunction::getFieldType, Function.identity()));
    }

    public FieldFunction getFieldFunction(FieldType fieldType) {
        return fieldTypeToFieldFunction.get(fieldType);
    }
}
