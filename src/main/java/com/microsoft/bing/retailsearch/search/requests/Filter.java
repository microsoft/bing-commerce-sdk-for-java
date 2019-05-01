package com.microsoft.bing.retailsearch.search.requests;

public final class Filter extends AggregationBase {

    private String value;

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
