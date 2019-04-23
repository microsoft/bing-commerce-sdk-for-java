package com.microsoft.bing.retailsearch.search.responses;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class SearchResponse {
    private int statusCode;
    private Query query;
    private Items items;
    private List<Aggregation> aggregations;
    private List<Error> errors;

    public int getStatusCode() { return this.statusCode; }

    public boolean isErrorResponse() {
        if (this.statusCode < 200 || this.statusCode > 299) {
            return true;
        }

        return false;
    }

    public Query getQuery() { return this.query; }

    public Items getItems() { return this.items; }

    public List<Aggregation> getAggregations() { return this.aggregations; }

    public List<Error> getErrors() { return this.errors; }

    public static SearchResponse fromHttpResponse(HttpResponse httpResponse) {
        try {
            ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            String body = EntityUtils.toString(httpResponse.getEntity());
            SearchResponse searchResponse = mapper.readValue(body, SearchResponse.class);
            searchResponse.statusCode = httpResponse.getStatusLine().getStatusCode();
            return searchResponse;
        } catch (IOException e) {
            return null;
        }
    }
}
