/**
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package com.microsoft.bing.commerce.search.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonSubTypes;

/**
 * The ResponseDebugInfo model.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "_type", defaultImpl = ResponseDebugInfo.class)
@JsonTypeName("Response.DebugInfo")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "BingItemDebugInfo", value = ResponseBingBingItemDebugInfo.class)
})
public class ResponseDebugInfo {
}
