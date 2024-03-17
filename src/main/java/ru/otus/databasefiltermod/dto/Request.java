package ru.otus.databasefiltermod.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    private List<String> uniqueFields;
    private Filter filter;
    private Integer limit = 25;
    private Integer offset = 0;
}
