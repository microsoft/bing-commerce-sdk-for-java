/**
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package com.microsoft.bing.commerce.search.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonSubTypes;

/**
 * Defines an aggregation result.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "_type", defaultImpl = ResponseAggregation.class)
@JsonTypeName("Aggregation")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "Filter", value = ResponseFilter.class),
    @JsonSubTypes.Type(name = "DiscoveredFacets", value = ResponseDiscoveredFacets.class),
    @JsonSubTypes.Type(name = "Response.RefinementBase", value = ResponseRefinementBase.class),
    @JsonSubTypes.Type(name = "Response.FieldAggregationBase", value = ResponseFieldAggregationBase.class)
})
public class ResponseAggregation extends ResponseTask {
    /**
     * The aggregation name as defined in the requset.
     */
    @JsonProperty(value = "name")
    private String name;

    /**
     * An estimated count of items in this aggregation.
     */
    @JsonProperty(value = "estimatedCount")
    private Long estimatedCount;

    /**
     * The list of child aggregations, if any.
     */
    @JsonProperty(value = "aggregations")
    private List<ResponseAggregation> aggregations;

    /**
     * Get the aggregation name as defined in the requset.
     *
     * @return the name value
     */
    public String name() {
        return this.name;
    }

    /**
     * Set the aggregation name as defined in the requset.
     *
     * @param name the name value to set
     * @return the ResponseAggregation object itself.
     */
    public ResponseAggregation withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get an estimated count of items in this aggregation.
     *
     * @return the estimatedCount value
     */
    public Long estimatedCount() {
        return this.estimatedCount;
    }

    /**
     * Set an estimated count of items in this aggregation.
     *
     * @param estimatedCount the estimatedCount value to set
     * @return the ResponseAggregation object itself.
     */
    public ResponseAggregation withEstimatedCount(Long estimatedCount) {
        this.estimatedCount = estimatedCount;
        return this;
    }

    /**
     * Get the list of child aggregations, if any.
     *
     * @return the aggregations value
     */
    public List<ResponseAggregation> aggregations() {
        return this.aggregations;
    }

    /**
     * Set the list of child aggregations, if any.
     *
     * @param aggregations the aggregations value to set
     * @return the ResponseAggregation object itself.
     */
    public ResponseAggregation withAggregations(List<ResponseAggregation> aggregations) {
        this.aggregations = aggregations;
        return this;
    }

}
