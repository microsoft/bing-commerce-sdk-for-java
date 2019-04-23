package com.microsoft.bing.retailsearch.search.requests;

import java.util.List;

public abstract class AggregationBase {
    private String name;
    private List<AggregationBase> aggregations;

    public String getName() {
        return this.name;
    }

    public AggregationBase setName(String name) {
        this.name = name;
        return this;
    }

    public List<AggregationBase> getAggregations() {
        return this.aggregations;
    }

    public AggregationBase setAggregations(List<AggregationBase> aggregations) {
        this.aggregations = aggregations;
        return this;
    }
}
