package com.microsoft.bing.retailsearch.search;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.bing.retailsearch.search.requests.SearchRequest;
import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;

public class SearchClient implements Closeable {
    private static final String urlFormat
        = "https://www.bingapis.com/api/retailsearch/api/v1/retail/%s/search";
    private static final String appIdParameterName = "appId";

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

    public final HttpResponse search(SearchRequest searchRequest) throws Exception {
        URI uri = new URIBuilder(String.format(urlFormat, searchRequest.getIndex()))
            .addParameter(appIdParameterName, this.appId)
            .build();

        HttpPost request = new HttpPost(uri);
        request.setHeader("content-type", "application/json");
        request.setHeader("tenantId", this.tenantId);
        request.setHeader("subscriptionId", this.subscriptionId);

        String jsonBody = this.mapper.writeValueAsString(searchRequest);
        request.setEntity(new StringEntity(jsonBody));

        HttpResponse response = this.client.execute(request, null).get();
        return response;
    }

    public final void searchAsync(SearchRequest searchRequest) {
        return;
    }

    public void close() throws IOException {
        this.client.close();
    }
}
