package br.com.mmmsieto.financial.annotation.cache;

import br.com.mmmsieto.financial.annotation.Mandatory;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ValidationCache {
    private static final Map<Class<?>, List<Field>> mandatoryFieldsCache = new HashMap<>();

    public static List<Field> getMandatoryFields(Class<?> clazz) {
        if (!mandatoryFieldsCache.containsKey(clazz)) {
            List<Field> mandatoryFields = Arrays.stream(clazz.getDeclaredFields())
                    .filter(field -> field.isAnnotationPresent(Mandatory.class))
                    .collect(Collectors.toList());
            mandatoryFieldsCache.put(clazz, mandatoryFields);
        }
        return mandatoryFieldsCache.get(clazz);
    }
}
