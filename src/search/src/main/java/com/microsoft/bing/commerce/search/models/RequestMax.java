/**
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package com.microsoft.bing.commerce.search.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Aggregation type for getting the max value of a field. Only applicable to
 * numeric data.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "_type", defaultImpl = RequestMax.class)
@JsonTypeName("Max")
public class RequestMax extends RequestFieldAggregationBase {
}
