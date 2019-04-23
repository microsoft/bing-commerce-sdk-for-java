package com.microsoft.bing.retailsearch.search.requests;

public final class Facet extends AggregationBase {
    private String field;
    private String orderBy;

    @Override
    public Facet setName(String name) {
        super.setName(name);
        return this;
    }

    public String getField() {
        return this.field;
    }

    public Facet setField(String field) {
        this.field = field;
        return this;
    }

    public String getOrderBy() {
        return this.orderBy;
    }

    public Facet setOrderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }
}
