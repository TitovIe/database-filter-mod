package ru.otus.databasefiltermod.parser;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
public class OperatorParserFactory {
    private final Map<String, OperatorParser> operatorToParser;

    public OperatorParserFactory(List<OperatorParser> operatorParsers) {
        this.operatorToParser = operatorParsers.stream()
                .collect(Collectors.toMap(OperatorParser::getOperatorTypeName, Function.identity()));
    }

    public OperatorParser getOperatorParser(String operatorTypeName) {
        return operatorToParser.get(operatorTypeName);
    }
}
