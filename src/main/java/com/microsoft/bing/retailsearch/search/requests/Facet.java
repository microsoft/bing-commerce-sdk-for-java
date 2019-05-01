package com.microsoft.bing.retailsearch.search.requests;

public final class Facet extends AggregationBase {

    private String field;
    private String orderBy;

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOrderBy() {
        return this.orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
