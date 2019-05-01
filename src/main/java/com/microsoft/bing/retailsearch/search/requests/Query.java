package com.microsoft.bing.retailsearch.search.requests;

import java.util.List;

public final class Query {

    private String matchAll;
    private List<Match> value;
    private String filter;

    public String getMatchAll() {
        return this.matchAll;
    }

    public void setMatchAll(String matchAll) {
        this.matchAll = matchAll;
    }

    public List<Match> getValue() {
        return this.value;
    }

    public void setValue(List<Match> value) {
        this.value = value;
    }

    public String getFilter() {
        return this.filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
