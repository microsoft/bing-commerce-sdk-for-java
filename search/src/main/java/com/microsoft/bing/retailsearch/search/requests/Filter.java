package com.microsoft.bing.retailsearch.search.requests;

public final class Filter extends AggregationBase {
    private String value;

    @Override
    public Filter setName(String name) {
        super.setName(name);
        return this;
    }

    public String getValue() {
        return this.value;
    }

    public Filter setValue(String value) {
        this.value = value;
        return this;
    }
}
