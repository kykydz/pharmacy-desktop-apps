package org.app.projectpharmacy.utils;

public class NameConventionConverter {
    public static String camelToSnakeCase(String camelCase) {
        StringBuilder snakeCase = new StringBuilder();
        boolean nextToUpperCase = false;
        for (char c : camelCase.toCharArray()) {
            if (Character.isUpperCase(c)) {
                snakeCase.append('_');
                nextToUpperCase = true;
            } else if (nextToUpperCase) {
                snakeCase.append(Character.toLowerCase(c));
                nextToUpperCase = false;
            } else {
                snakeCase.append(c);
            }
        }
        return snakeCase.toString().toLowerCase();
    }

    public static String snakeToCamelCase(String snakeCase) {
        StringBuilder camelCase = new StringBuilder();
        boolean nextToLowerCase = false;
        for (char c : snakeCase.toCharArray()) {
            if (c == '_') {
                nextToLowerCase = true;
            } else if (nextToLowerCase) {
                camelCase.append(Character.toUpperCase(c));
                nextToLowerCase = false;
            } else {
                camelCase.append(c);
            }
        }
        return camelCase.toString();
    }
}
