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

    public void setIndexId(UUID indexId) {
        this.indexId = indexId;
    }

    public Query getQuery() {
        return this.query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public Items getItems() {
        return this.items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public List<AggregationBase> getAggregations() {
        return this.aggregations;
    }

    public void setAggregations(List<AggregationBase> aggregations) {
        this.aggregations = aggregations;
    }
}
