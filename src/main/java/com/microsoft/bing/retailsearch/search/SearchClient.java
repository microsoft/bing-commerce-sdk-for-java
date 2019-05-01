package com.microsoft.bing.retailsearch.search;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.bing.retailsearch.common.ResponseListener;
import com.microsoft.bing.retailsearch.common.RetailSearchClient;
import com.microsoft.bing.retailsearch.common.RetailSearchClientBuilder;
import com.microsoft.bing.retailsearch.common.requests.Request;
import com.microsoft.bing.retailsearch.common.requests.Request.RequestMethod;
import com.microsoft.bing.retailsearch.common.responses.Response;
import com.microsoft.bing.retailsearch.search.requests.SearchRequest;
import com.microsoft.bing.retailsearch.search.responses.SearchResponse;
import java.io.Closeable;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.net.URISyntaxException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import org.apache.http.entity.StringEntity;

public class SearchClient implements Closeable {
    // TODO: change to external url for api
    private static final String URL_FORMAT
        = "https://retailsearch.asgfalcon-test.io/api/v1/retail/%s/search";
    private static final String TENANTID_HEADER_NAME = "tenantId";

    private final ObjectMapper mapper;
    private final RetailSearchClient client;
    private final UUID tenantId;

    SearchClient(RetailSearchClientBuilder clientBuilder, UUID defaultTenantId) {
        this.mapper = new ObjectMapper()
            .setSerializationInclusion(Include.NON_NULL);

        this.client = clientBuilder.build();
        this.tenantId = defaultTenantId;
    }

    public final SearchResponse search(SearchRequest searchRequest)
        throws URISyntaxException, ExecutionException, InterruptedException, IOException  {
        Request request = this.createRequest(searchRequest);
        Response response = this.client.performRequest(request);
        SearchResponse searchResponse = response.mapToApiResponse(SearchResponse.class);

        return searchResponse;
    }

    public final void searchAsync(SearchRequest searchRequest, final ResponseListener<SearchResponse> responseListener) {
        try {
            Request request = this.createRequest(searchRequest);
            this.client.performRequestAsync(request, new ResponseListener<Response>() {
                public void onSuccess(Response response) {
                    if (!response.isErrorResponse()) {
                        try {
                            SearchResponse searchResponse = response.mapToApiResponse(SearchResponse.class);
                            responseListener.onSuccess(searchResponse);
                        } catch (IOException e) {
                            responseListener.onFailure(e);
                        }
                    }
                }

                public void onFailure(Exception e) {
                    responseListener.onFailure(e);
                }
            });
        } catch (Exception e) {
            responseListener.onFailure(e);
        }
    }

    public void close() throws IOException {
        this.client.close();
    }

    private Request createRequest(SearchRequest searchRequest) throws InvalidObjectException {
        String endpoint = String.format(URL_FORMAT, searchRequest.getIndexId());

        // TODO: support GET requests

        Request request = new Request(RequestMethod.POST, endpoint);
        request.addHeader(TENANTID_HEADER_NAME, this.tenantId.toString());

        // TODO: remove after switching to prod endpoint
        request.addParameter("traffictype", "test");

        try {
            String jsonBody = this.mapper.writeValueAsString(searchRequest);
            request.setEntity(new StringEntity(jsonBody));
        } catch (Exception e) {
            throw new InvalidObjectException("Error serializing the SearchRequest");
        }

        return request;
    }
}
