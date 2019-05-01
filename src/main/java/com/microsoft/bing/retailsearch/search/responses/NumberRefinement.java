package com.microsoft.bing.retailsearch.search.responses;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "_type",
    defaultImpl = NumberRefinement.class)
public class NumberRefinement extends RefinementBase {
    private Double value;

    public Double getValue() { return this.value; }
}
