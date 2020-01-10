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

/**
 * The RequestBingMatchStreams model.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "_type", defaultImpl = RequestBingMatchStreams.class)
@JsonTypeName("MatchStreams")
public class RequestBingMatchStreams extends RequestMatch {
    /**
     * The include property.
     */
    @JsonProperty(value = "include")
    private List<String> include;

    /**
     * The exclude property.
     */
    @JsonProperty(value = "exclude")
    private List<String> exclude;

    /**
     * Get the include value.
     *
     * @return the include value
     */
    public List<String> include() {
        return this.include;
    }

    /**
     * Set the include value.
     *
     * @param include the include value to set
     * @return the RequestBingMatchStreams object itself.
     */
    public RequestBingMatchStreams withInclude(List<String> include) {
        this.include = include;
        return this;
    }

    /**
     * Get the exclude value.
     *
     * @return the exclude value
     */
    public List<String> exclude() {
        return this.exclude;
    }

    /**
     * Set the exclude value.
     *
     * @param exclude the exclude value to set
     * @return the RequestBingMatchStreams object itself.
     */
    public RequestBingMatchStreams withExclude(List<String> exclude) {
        this.exclude = exclude;
        return this;
    }

}
