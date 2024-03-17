package ru.otus.databasefiltermod.enums;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum FieldType {
    MANY_TO_MANY(ManyToMany.class),
    ONE_TO_MANY(OneToMany.class),
    MANY_TO_ONE(ManyToOne.class);

    private final Class<? extends Annotation> annotationClassType;

    public static FieldType findByAnnotationClassType(Class<? extends Annotation> annotationClassType) {
        return Arrays.stream(FieldType.values())
                .filter(fieldType -> fieldType.getAnnotationClassType().equals(annotationClassType))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(annotationClassType.getName()));
    }

    public static List<Class<? extends Annotation>> getAnnotationClassTypes() {
        return Arrays.stream(FieldType.values()).map(FieldType::getAnnotationClassType).collect(Collectors.toList());
    }
}
