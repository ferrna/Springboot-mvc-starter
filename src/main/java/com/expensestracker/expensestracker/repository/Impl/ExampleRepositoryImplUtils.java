package com.expensestracker.expensestracker.repository.Impl;

import com.expensestracker.expensestracker.model.Example;
import com.expensestracker.expensestracker.repository.Impl.Utils.QueryResult;

import java.util.LinkedHashMap;
import java.util.Map;

public class ExampleRepositoryImplUtils {
    private static final String table_name = "examples";

    public static QueryResult buildUpdateQuery(Long id, Example example){

        /*
        This approach is good because it combines flexibility, efficiency, and clarity.
        It dynamically constructs SQL queries based on the provided data, and ensures that only
        the necessary fields are updated. Well-structured for making it easy to understand and
        maintain. Additionally, it avoids potential pitfalls of hardcoding queries or using
        inefficient update methods.
         */

        // Step 1: Create a map of field values that need to be updated (only non-null)
        Map<String, Object> fieldsToUpdate = new LinkedHashMap<>();

        if (example.getName() != null) {
            fieldsToUpdate.put("name", example.getName());
        }

        if (example.getGenre() != null) {
            fieldsToUpdate.put("genre", example.getGenre());
        }

        if (example.getPrice() != null) {
            fieldsToUpdate.put("price", example.getPrice());
        }

        if (fieldsToUpdate.isEmpty()) {
            // No fields to update
            return null;
        }

        // Step 2: Dynamically build the SQL update query based on the fieldsToUpdate map
        StringBuilder query = new StringBuilder("UPDATE " + table_name + " SET ");
        fieldsToUpdate.forEach((field, value) -> query.append(field).append(" = ?, "));

        // Remove the last comma and space
        query.setLength(query.length() - 2);

        // Append the WHERE clause
        query.append(" WHERE id = ?");

        // Convert map values to array for the query parameters
        Object[] params = new Object[fieldsToUpdate.size() + 1];
        int index = 0;
        for (Object value : fieldsToUpdate.values()) {
            params[index++] = value;
        }
        params[index] = id; // id is the last parameter

        // Step 3: Return the stringBuilder and params
        return new QueryResult(query.toString(), params);
    }
}
