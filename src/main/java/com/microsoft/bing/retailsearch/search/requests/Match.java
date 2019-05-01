package com.microsoft.bing.retailsearch.search.requests;

public final class Match extends QueryClauseBase {

    private String field;
    private String value;

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
