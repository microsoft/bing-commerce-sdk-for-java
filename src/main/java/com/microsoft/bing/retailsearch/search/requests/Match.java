package com.microsoft.bing.retailsearch.search.requests;

public final class Match extends QueryClauseBase {
    private String field;
    private String value;

    public String getField() {
        return this.field;
    }

    public Match setField(String field) {
        this.field = field;
        return this;
    }

    public String getValue() {
        return this.value;
    }

    public Match setValue(String value) {
        this.value = value;
        return this;
    }
}
