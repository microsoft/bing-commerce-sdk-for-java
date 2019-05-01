package com.microsoft.bing.retailsearch.search.responses;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.List;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "_type",
    defaultImpl = Aggregation.class)
@JsonSubTypes({
    @JsonSubTypes.Type(value = DiscoveredFacets.class, name = "DiscoveredFacets"),
    @JsonSubTypes.Type(value = BooleanFacet.class, name = "BoolFacet"),
    @JsonSubTypes.Type(value = NumberFacet.class, name = "NumberFacet"),
    @JsonSubTypes.Type(value = RangeFacet.class, name = "RangeFacet"),
    @JsonSubTypes.Type(value = StringFacet.class, name = "StringFacet") }
)
public class Aggregation {
    private String name;
    private int estimatedCount;
    private List<Aggregation> aggregations;

    public String getName() { return this.name; }

    public int getEstimatedCount() { return this.estimatedCount; }

    public List<Aggregation> getAggregations() { return this.aggregations; }
}
