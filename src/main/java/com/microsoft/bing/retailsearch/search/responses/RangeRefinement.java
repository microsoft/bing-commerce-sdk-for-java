package com.microsoft.bing.retailsearch.search.responses;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "_type",
    defaultImpl = RangeRefinement.class)
public class RangeRefinement extends RefinementBase {
    private double min;
    private double max;

    public double getMin() { return this.min; }

    public double getMax() { return this.max; }
}
