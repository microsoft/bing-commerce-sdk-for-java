package com.microsoft.bing.retailsearch.search.responses;

import java.util.List;

public abstract class FacetBase<T> extends Aggregation {
    public List<T> refinements;

    public List<T> getRefinements() { return this.refinements; }
}
