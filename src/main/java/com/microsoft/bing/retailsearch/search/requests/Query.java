package com.microsoft.bing.retailsearch.search.requests;

import java.util.List;

public final class Query {
    private String matchAll;
    private List<Match> value;
    private String filter;

    public String getMatchAll() {
        return this.matchAll;
    }

    public Query setMatchAll(String matchAll) {
        this.matchAll = matchAll;
        return this;
    }

    public List<Match> getValue() {
        return this.value;
    }

    public Query setValue(List<Match> value) {
        this.value = value;
        return this;
    }

    public String getFilter() {
        return this.filter;
    }

    public Query setFilter(String filter) {
        this.filter = filter;
        return this;
    }
}
