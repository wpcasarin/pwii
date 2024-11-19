package br.edu.ifto.pwii.utils;

import java.lang.reflect.Field;
import java.util.Objects;

public class EntityUtils {
    /**
     * Checks if any fields have changed between two entities using reflection.
     *
     * @param existingEntity The existing entity (usually the one fetched from the database)
     * @param updatedEntity  The updated entity (usually the one containing user input)
     * @return true if any field has changed, false otherwise
     */
    public static <T> boolean hasChanged(T existingEntity, T updatedEntity) {
        boolean isChanged = false;

        if (!existingEntity.getClass().equals(updatedEntity.getClass())) {
            throw new IllegalArgumentException("Entities must be of the same type");
        }

        for (Field field : existingEntity.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            if ("id".equals(field.getName()) || "createdAt".equals(field.getName()) || "updatedAt".equals(field.getName())) {
                continue;
            }

            try {
                Object existingValue = field.get(existingEntity);
                Object newValue = field.get(updatedEntity);

                if (!Objects.equals(existingValue, newValue)) {
                    field.set(existingEntity, newValue);  // Update the field if values are different
                    isChanged = true;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Update failed due to reflection error: " + e.getMessage());
            }
        }
        
        return isChanged;
    }
}
