package ru.otus.databasefiltermod.util;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@UtilityClass
public class PostgresUtil {
    public Set<String> getFieldsByClass(Class<?> classType) {
        List<Field> fields = new ArrayList<>();
        fillDeclaredFields(classType, fields);
        return fields.stream().map(Field::getName).collect(Collectors.toSet());
    }

    private void fillDeclaredFields(Class<?> classType, List<Field> fields) {
        fields.addAll(Arrays.stream(classType.getDeclaredFields()).toList());
        Class<?> superClassType = classType.getSuperclass();
        if (!Objects.equals(superClassType, Object.class)) {
            fillDeclaredFields(superClassType, fields);
        }
    }
}
