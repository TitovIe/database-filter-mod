package ru.otus.databasefiltermod.service;

import ru.otus.databasefiltermod.dto.Request;
import ru.otus.databasefiltermod.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findEntitiesByRequest(Request request);
}
