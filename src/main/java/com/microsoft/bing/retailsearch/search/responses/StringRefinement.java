package com.microsoft.bing.retailsearch.search.responses;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "_type",
    defaultImpl = StringRefinement.class)
public class StringRefinement extends RefinementBase {

    private String value;

    public String getValue() {
        return this.value;
    }
}
