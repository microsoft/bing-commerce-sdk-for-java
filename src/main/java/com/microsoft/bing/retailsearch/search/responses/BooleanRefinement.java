package com.microsoft.bing.retailsearch.search.responses;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "_type",
    defaultImpl = BooleanRefinement.class)
public class BooleanRefinement extends RefinementBase {
    private boolean value;

    public boolean getValue() { return this.value; }
}
