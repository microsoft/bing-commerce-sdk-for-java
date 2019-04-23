package com.microsoft.bing.retailsearch.search;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.bing.retailsearch.search.requests.SearchRequest;
import com.microsoft.bing.retailsearch.search.responses.SearchResponse;
import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;

public class SearchClient implements Closeable {
    // TODO: change to url for SDK api
    private static final String URL_FORMAT
        = "https://www.bingapis.com/api/retailsearch/api/v1/retail/%s/search";
    private static final String APPID_PARAMETER_NAME = "appId";
    private static final String CONTENT_TYPE_HEADER_NAME = "content-type";
    private static final String APPLICATION_JSON_CONTENT_TYPE = "application/json";
    private static final String TENANTID_HEADER_NAME = "tenantId";
    private static final String SUBSCRIPTIONID_HEADER_NAME = "subscriptionId";

    private final ObjectMapper mapper;
    private final CloseableHttpAsyncClient client;
    private final String appId;
    private final String subscriptionId;
    private final String tenantId;

    SearchClient(CloseableHttpAsyncClient client, String appId, String subscriptionId, String tenantId) {
        this.mapper = new ObjectMapper()
            .setSerializationInclusion(Include.NON_NULL);

        this.client = client;
        this.appId = appId;
        this.subscriptionId = subscriptionId;
        this.tenantId = tenantId;
    }

    public final SearchResponse search(SearchRequest searchRequest) throws Exception {
        URI uri = new URIBuilder(String.format(URL_FORMAT, searchRequest.getIndex()))
            .addParameter(APPID_PARAMETER_NAME, this.appId)
            .build();

        HttpPost request = new HttpPost(uri);
        request.setHeader(CONTENT_TYPE_HEADER_NAME, APPLICATION_JSON_CONTENT_TYPE);
        request.setHeader(TENANTID_HEADER_NAME, this.tenantId);
        if (this.subscriptionId != null && this.subscriptionId.trim().length() > 0) {
            request.setHeader(SUBSCRIPTIONID_HEADER_NAME, this.subscriptionId);
        }

        String jsonBody = this.mapper.writeValueAsString(searchRequest);
        request.setEntity(new StringEntity(jsonBody));

        HttpResponse httpResponse = this.client.execute(request, null).get();
        SearchResponse searchResponse = SearchResponse.fromHttpResponse(httpResponse);

        return searchResponse;
    }

    public final void searchAsync(SearchRequest searchRequest) {
        return;
    }

    public void close() throws IOException {
        this.client.close();
    }
}
