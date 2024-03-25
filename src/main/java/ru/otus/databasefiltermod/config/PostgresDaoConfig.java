package ru.otus.databasefiltermod.config;

import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.otus.databasefiltermod.dao.PostgresDao;
import ru.otus.databasefiltermod.entity.Employee;
import ru.otus.databasefiltermod.expression.specification.FilterSpecification;
import ru.otus.databasefiltermod.parser.TargetParserFactory;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Configuration
public class PostgresDaoConfig {
    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public PostgresDao<Employee> employeePostgresDao(FilterSpecification<Employee> filterSpecification,
                                                     EntityManager entityManager,
                                                     TargetParserFactory targetParserFactory) {
        return new PostgresDao<>(Employee.class, filterSpecification, entityManager, targetParserFactory);
    }
}
