package com.microsoft.bing.retailsearch.search.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import java.util.UUID;

public class SearchRequest {
    @JsonIgnore
    private UUID indexId;
    private Query query;
    private Items items;
    private List<AggregationBase> aggregations;

    public UUID getIndexId() {
        return this.indexId;
    }

    public SearchRequest setIndex(UUID indexId) {
        this.indexId = indexId;
        return this;
    }

    public Query getQuery() {
        return this.query;
    }

    public SearchRequest setQuery(Query query) {
        this.query = query;
        return this;
    }

    public Items getItems() {
        return this.items;
    }

    public SearchRequest setItems(Items items) {
        this.items = items;
        return this;
    }

    public List<AggregationBase> getAggregations() {
        return this.aggregations;
    }

    public SearchRequest setAggregations(List<AggregationBase> aggregations) {
        this.aggregations = aggregations;
        return this;
    }
}
