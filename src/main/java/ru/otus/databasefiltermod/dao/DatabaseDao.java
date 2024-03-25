package ru.otus.databasefiltermod.dao;

import ru.otus.databasefiltermod.dto.Request;

import java.util.List;

public interface DatabaseDao<T> {
    List<T> findEntitiesByRequest(Request request);
}
