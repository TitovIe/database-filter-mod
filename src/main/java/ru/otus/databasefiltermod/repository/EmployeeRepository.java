package ru.otus.databasefiltermod.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.databasefiltermod.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @EntityGraph(value = "all-entity-graph")
    List<Employee> findAllWithAssociationsBy();
}