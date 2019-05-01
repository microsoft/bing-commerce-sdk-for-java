package com.microsoft.bing.retailsearch.search.requests;

public final class DiscoverFacets extends AggregationBase {

    private boolean discoverFilter = true;

    public boolean getDiscoverFilter() {
        return this.discoverFilter;
    }

    public void setDiscoverFilter(boolean discoverFilter) {
        this.discoverFilter = discoverFilter;
    }
}
