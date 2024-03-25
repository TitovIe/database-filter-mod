package ru.otus.databasefiltermod.service;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import ru.otus.databasefiltermod.dao.PostgresDao;
import ru.otus.databasefiltermod.dto.Request;
import ru.otus.databasefiltermod.entity.Employee;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public List<Employee> findEntitiesByRequest(Request request) {
        return employeePostgresDao().findEntitiesByRequest(request);
    }
    @Lookup
    public PostgresDao<Employee> employeePostgresDao() {
        return null;
    }
}
