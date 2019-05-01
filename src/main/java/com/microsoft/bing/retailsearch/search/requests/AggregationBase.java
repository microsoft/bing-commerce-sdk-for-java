package com.microsoft.bing.retailsearch.search.requests;

import java.util.List;

public abstract class AggregationBase {

    private String name;
    private List<AggregationBase> aggregations;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AggregationBase> getAggregations() {
        return this.aggregations;
    }

    public void setAggregations(List<AggregationBase> aggregations) {
        this.aggregations = aggregations;
    }
}
