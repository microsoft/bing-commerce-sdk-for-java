package com.microsoft.bing.retailsearch.search.responses;

import java.util.List;

public class Items {

    private List<Item> value;
    private Long totalEstimatedMatches;
    private Pagination pagination;

    public List<Item> getValue() {
        return this.value;
    }

    public Long getTotalEstimatedMatches() {
        return this.totalEstimatedMatches;
    }

    public Pagination getPagination() {
        return this.pagination;
    }
}
