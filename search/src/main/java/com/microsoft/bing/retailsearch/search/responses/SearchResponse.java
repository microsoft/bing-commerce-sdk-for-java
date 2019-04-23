package com.microsoft.bing.retailsearch.search.responses;

import java.util.List;

public class SearchResponse {
    private Query query;
    private Items items;
    private List<Aggregation> aggregations;

    public Query getQuery() { return this.query; }

    public Items getItems() { return this.items; }

    public List<Aggregation> getAggregations() { return this.aggregations; }
}
