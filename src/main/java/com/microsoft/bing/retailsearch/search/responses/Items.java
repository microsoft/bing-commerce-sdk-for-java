package com.microsoft.bing.retailsearch.search.responses;

import java.util.List;

public class Items {
    private List<Item> value;
    private long totalEstimatedMatches;
    private Pagination pagination;

    public List<Item> getValue() { return this.value; }

    public long getTotalEstimatedMatches() { return this.totalEstimatedMatches; }

    public Pagination getPagination() { return this.pagination; }
}
