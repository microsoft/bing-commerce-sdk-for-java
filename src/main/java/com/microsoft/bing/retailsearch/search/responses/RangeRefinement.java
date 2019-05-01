package com.microsoft.bing.retailsearch.search.responses;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "_type",
    defaultImpl = RangeRefinement.class)
public class RangeRefinement extends RefinementBase {

    private Double min;
    private Double max;

    public Double getMin() {
        return this.min;
    }

    public Double getMax() {
        return this.max;
    }
}
