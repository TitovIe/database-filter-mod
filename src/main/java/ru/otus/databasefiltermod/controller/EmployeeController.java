package ru.otus.databasefiltermod.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.databasefiltermod.dto.Request;
import ru.otus.databasefiltermod.entity.Employee;
import ru.otus.databasefiltermod.service.EmployeeService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<List<Employee>> findEntitiesByRequest(@RequestBody Request request) {
        List<Employee> response = employeeService.findEntitiesByRequest(request);
        return ResponseEntity.ok(response);
    }
}
