package ru.otus.databasefiltermod.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import ru.otus.databasefiltermod.dto.Filter;
import ru.otus.databasefiltermod.dto.Request;
import ru.otus.databasefiltermod.expression.FilterContext;
import ru.otus.databasefiltermod.expression.FilterExpression;
import ru.otus.databasefiltermod.expression.specification.FilterSpecification;
import ru.otus.databasefiltermod.parser.TargetParserFactory;
import ru.otus.databasefiltermod.util.PostgresUtil;

import java.util.List;

@RequiredArgsConstructor
public class PostgresDao<T> implements DatabaseDao<T> {
    private final Class<T> classType;
    private final FilterSpecification<T> filterSpecification;
    private final EntityManager entityManager;
    private final TargetParserFactory targetParserFactory;

    @Override
    public List<T> findEntitiesByRequest(Request request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(classType);
        Root<T> root = criteriaQuery.from(classType);

        FilterContext filterContext = FilterContext.builder()
                .filterSpecification(filterSpecification)
                .criteriaBuilder(criteriaBuilder)
                .criteriaQuery(criteriaQuery)
                .root(root)
                .classType(classType)
                .fields(PostgresUtil.getFieldsByClass(classType))
                .build();
        Filter filter = request.getFilter();
        FilterExpression expression = targetParserFactory.getTargetParser(filter.getTarget()).parse(filter);
        Predicate[] predicates = expression.interpret(filterContext);

        criteriaQuery.select(root).where(predicates);

        return entityManager.createQuery(criteriaQuery)
                .setFirstResult(request.getOffset())
                .setMaxResults(request.getLimit())
                .getResultList();
    }
}
