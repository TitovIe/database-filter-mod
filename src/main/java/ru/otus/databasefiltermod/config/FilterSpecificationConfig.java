package ru.otus.databasefiltermod.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.otus.databasefiltermod.entity.Employee;
import ru.otus.databasefiltermod.entity.Role;
import ru.otus.databasefiltermod.expression.specification.FilterFunctionFactory;
import ru.otus.databasefiltermod.expression.specification.FilterSpecification;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Configuration
public class FilterSpecificationConfig {
    @Bean
    public FilterSpecification<Employee> userFilterSpecification(FilterFunctionFactory filterFunctionFactory) {
        return new FilterSpecification<>(filterFunctionFactory);
    }

    @Bean
    public FilterSpecification<Role> roleFilterSpecification(FilterFunctionFactory filterFunctionFactory) {
        return new FilterSpecification<>(filterFunctionFactory);
    }
}
