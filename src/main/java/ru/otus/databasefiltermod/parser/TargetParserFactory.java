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
public class TargetParserFactory {
    private final Map<String, TargetParser> targetToParser;

    public TargetParserFactory(List<TargetParser> targetParsers) {
        this.targetToParser = targetParsers.stream()
                .collect(Collectors.toMap(TargetParser::getTargetTypeName, Function.identity()));
    }

    public TargetParser getTargetParser(String targetTypeName) {
        return targetToParser.get(targetTypeName);
    }
}
