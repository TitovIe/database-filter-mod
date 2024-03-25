package ru.otus.databasefiltermod.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Filter {
    private String target;
    private Map<String, Object> args;
    private List<Filter> filters;
}
