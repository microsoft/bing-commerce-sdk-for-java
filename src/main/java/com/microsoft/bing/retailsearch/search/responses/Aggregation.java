package com.microsoft.bing.retailsearch.search.responses;

import java.util.List;

public class Aggregation {
    private String name;
    private int estimatedCount;
    private List<Aggregation> aggregations;

    public String getName() { return this.name; }

    public int getEstimatedCount() { return this.estimatedCount; }

    public List<Aggregation> getAggregations() { return this.aggregations; }
}
