package com.expensestracker.expensestracker.repository.Impl.Utils;

public class QueryResult {
    private final String query;
    private final Object[] params;

    public QueryResult(String query, Object[] params) {
        this.query = query;
        this.params = params;
    }

    public String getQuery() {
        return query;
    }

    public Object[] getParams() {
        return params;
    }
}
