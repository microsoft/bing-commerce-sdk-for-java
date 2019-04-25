package com.microsoft.bing.retailsearch.search.requests;

public final class DiscoverFacets extends AggregationBase {
    private boolean discoverFilter = true;

    @Override
    public DiscoverFacets setName(String name) {
        super.setName(name);
        return this;
    }

    public boolean getDiscoverFilter() {
        return this.discoverFilter;
    }

    public DiscoverFacets setDiscoverFilter(boolean discoverFilter) {
        this.discoverFilter = discoverFilter;
        return this;
    }
}
