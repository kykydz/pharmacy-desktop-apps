package org.app.projectpharmacy.utils;

import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Field;

public class ObjectMapperConvention {
    public static <T> Map<String, Object> toMap(T object, boolean toCamelCase) {
        Map<String, Object> map = new HashMap<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object fieldValue = field.get(object);
                if (toCamelCase) {
                    fieldName = NameConventionConverter.snakeToCamelCase(fieldName); // Use generic function
                }
                map.put(fieldName, fieldValue);
            } catch (Exception e) {
                // Handle exceptions appropriately (e.g., logging)
            }
        }
        return map;
    }

    public static <T> T fromMap(Map<String, Object> map, Class<T> targetClass, boolean fromCamelCase) {
        try {
            T object = targetClass.getDeclaredConstructor().newInstance();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                if (fromCamelCase) {
                    key = NameConventionConverter.camelToSnakeCase(key); // Use generic function
                }
                try {
                    Field field = targetClass.getDeclaredField(key);
                    field.setAccessible(true);
                    field.set(object, entry.getValue());
                } catch (NoSuchFieldException e) {
                    // Handle case where field might not exist (e.g., logging)
                }
            }
            return object;
        } catch (Exception e) {
            // Handle exceptions appropriately (e.g., logging)
            throw new RuntimeException("Error creating object from map", e);
        }
    }
}

