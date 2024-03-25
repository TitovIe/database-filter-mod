package ru.otus.databasefiltermod;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.otus.databasefiltermod.entity.Contact;
import ru.otus.databasefiltermod.entity.Employee;
import ru.otus.databasefiltermod.entity.Role;
import ru.otus.databasefiltermod.repository.EmployeeRepository;

import java.time.OffsetDateTime;
import java.util.List;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest(webEnvironment = DEFINED_PORT)
@AutoConfigureMockMvc
public class DatabaseFilterModIT {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:14-alpine");

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
        Employee employee1 = Employee.builder()
                .firstName("Ilya")
                .lastName("Titov")
                .hireDate(OffsetDateTime.parse("2010-12-03T10:15:30+01:00"))
                .active(Boolean.TRUE)
                .competencies(new String[]{"back", "team_lead"})
                .build();
        employee1.addUserField(Role.builder()
                .name("admin")
                .priority(1)
                .aliases(new String[]{"main", "master"})
                .build());
        employee1.addContact(Contact.builder()
                .data("8888-888-88-88")
                .type("phone")
                .active(true)
                .build());
        employee1.addContact(Contact.builder()
                .data("titov.ie@phystech.edu")
                .type("email")
                .active(true)
                .build());

        Employee employee2 = Employee.builder()
                .firstName("Sergey")
                .lastName("Ivanov")
                .hireDate(OffsetDateTime.parse("2015-12-03T10:15:30+01:00"))
                .active(Boolean.TRUE)
                .competencies(new String[]{"front", "scrum_master"})
                .build();
        employee2.addUserField(Role.builder()
                .name("prime_user")
                .priority(2)
                .aliases(new String[]{"main_user", "master_user"})
                .build());
        employee2.addContact(Contact.builder()
                .data("8777-777-77-77")
                .type("phone")
                .active(true)
                .build());
        employee2.addContact(Contact.builder()
                .data("ivanov.sergey@yandex.ru")
                .type("email")
                .active(true)
                .build());

        Employee employee3 = Employee.builder()
                .firstName("Alex")
                .lastName("Aksenov")
                .hireDate(OffsetDateTime.parse("2024-12-03T10:15:30+01:00"))
                .active(Boolean.FALSE)
                .competencies(new String[]{"back", "front"})
                .build();
        employee3.addUserField(Role.builder()
                .name("user")
                .priority(3)
                .aliases(new String[]{"default", "minimal"})
                .build());
        employee3.addContact(Contact.builder()
                .data("8555-555-55-55")
                .type("phone")
                .active(true)
                .build());
        employee3.addContact(Contact.builder()
                .data("aksenov.alex@yandex.ru")
                .type("email")
                .active(true)
                .build());

        employeeRepository.saveAll(List.of(employee1, employee2, employee3));
    }

    @SneakyThrows
    @Test
    void test1(){
        String request = "{\"limit\":25,\"offset\":0,\"filter\":{\"target\":\"filter\",\"args\":{\"operator\":\"or\"},\"filters\":[{\"target\":\"filter\",\"args\":{\"operator\":\"and\"},\"filters\":[{\"target\":\"property\",\"args\":{\"columnName\":\"hireDate\",\"operator\":\"greaterThan\",\"value\":\"2012-12-03T10:15:30+01:00\"}}]},{\"target\":\"filter\",\"args\":{\"operator\":\"and\"},\"filters\":[{\"target\":\"property\",\"args\":{\"columnName\":\"active\",\"operator\":\"equals\",\"value\":\"true\"}}]}]}}";
        mockMvc.perform(post("/api/employee")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andDo(print());
    }

    @SneakyThrows
    @Test
    void test2(){
        String request = "{\"limit\":25,\"offset\":0,\"filter\":{\"target\":\"filter\",\"args\":{\"operator\":\"or\"},\"filters\":[{\"target\":\"filter\",\"args\":{\"operator\":\"and\"},\"filters\":[{\"target\":\"property\",\"args\":{\"columnName\":\"hireDate\",\"operator\":\"greaterThan\",\"value\":\"2012-12-03T10:15:30+01:00\"}},{\"target\":\"property\",\"args\":{\"columnName\":\"active\",\"operator\":\"equals\",\"value\":\"true\"}}]},{\"target\":\"filter\",\"args\":{\"operator\":\"and\"},\"filters\":[{\"target\":\"field\",\"args\":{\"parentColumnName\":\"roles\",\"columnName\":\"priority\",\"operator\":\"in\",\"value\":\"1,4,6\"}}]}]}}";
        mockMvc.perform(post("/api/employee")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andDo(print());
    }

    @SneakyThrows
    @Test
    void test3(){
        String request = "{\"limit\":25,\"offset\":0,\"filter\":{\"target\":\"filter\",\"args\":{\"operator\":\"or\"},\"filters\":[{\"target\":\"filter\",\"args\":{\"operator\":\"and\"},\"filters\":[{\"target\":\"property\",\"args\":{\"columnName\":\"hireDate\",\"operator\":\"greaterThan\",\"value\":\"2012-12-03T10:15:30+01:00\"}},{\"target\":\"property\",\"args\":{\"columnName\":\"active\",\"operator\":\"equals\",\"value\":\"true\"}}]},{\"target\":\"filter\",\"args\":{\"operator\":\"and\"},\"filters\":[{\"target\":\"field\",\"args\":{\"parentColumnName\":\"contacts\",\"columnName\":\"active\",\"operator\":\"equals\",\"value\":\"true\"}}]}]}}";
        mockMvc.perform(post("/api/employee")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andDo(print());
    }

    @SneakyThrows
    @Test
    void test4(){
        String request = "{\"limit\":25,\"offset\":0,\"filter\":{\"target\":\"filter\",\"args\":{\"operator\":\"and\"},\"filters\":[{\"target\":\"filter\",\"args\":{\"operator\":\"or\"},\"filters\":[{\"target\":\"property\",\"args\":{\"columnName\":\"hireDate\",\"operator\":\"greaterThan\",\"value\":\"2012-12-03T10:15:30+01:00\"}},{\"target\":\"property\",\"args\":{\"columnName\":\"competencies\",\"operator\":\"containsOr\",\"value\":\"team_lead,scrum_master\"}}]},{\"target\":\"filter\",\"args\":{\"operator\":\"and\"},\"filters\":[{\"target\":\"field\",\"args\":{\"parentColumnName\":\"contacts\",\"columnName\":\"active\",\"operator\":\"equals\",\"value\":\"true\"}},{\"target\":\"field\",\"args\":{\"parentColumnName\":\"roles\",\"columnName\":\"priority\",\"operator\":\"in\",\"value\":\"1,4,6\"}}]}]}}";
        mockMvc.perform(post("/api/employee")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andDo(print());
    }
}
