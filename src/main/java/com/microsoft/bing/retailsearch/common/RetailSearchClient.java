package com.microsoft.bing.retailsearch.common;

import com.microsoft.bing.retailsearch.common.requests.Request;
import com.microsoft.bing.retailsearch.common.requests.Request.RequestMethod;
import com.microsoft.bing.retailsearch.common.responses.Response;
import com.microsoft.bing.retailsearch.common.responses.ResponseException;
import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;

public class RetailSearchClient implements Closeable {
    private static final String APPID_PARAMETER_NAME = "appId";
    private static final String CONTENT_TYPE_HEADER_NAME = "content-type";
    private static final String APPLICATION_JSON_CONTENT_TYPE = "application/json";
    private static final String SUBSCRIPTIONID_HEADER_NAME = "subscriptionId";

    private final CloseableHttpAsyncClient client;
    private final String appId;
    private final String subscriptionId;

    RetailSearchClient(CloseableHttpAsyncClient client, String appId, String subscriptionId) {
        this.client = client;
        this.appId = appId;
        this.subscriptionId = subscriptionId;
    }

    public Response performRequest(Request request)
        throws URISyntaxException, ExecutionException, InterruptedException, IOException {
        HttpRequestBase httpRequest = this.createHttpRequest(request);

        HttpResponse httpResponse = this.client.execute(httpRequest, null).get();

        Response response = new Response(httpResponse);
        if (response.isErrorResponse()) {
            throw new ResponseException(response);
        }

        return response;
    }

    public void performRequestAsync(Request request, final ResponseListener<Response> responseListener) {
        try {
            HttpRequestBase httpRequest = this.createHttpRequest(request);
            this.client.execute(httpRequest, new FutureCallback<HttpResponse>() {
                public void completed(HttpResponse httpResponse) {
                    Response response = new Response(httpResponse);
                    if (!response.isErrorResponse()) {
                        responseListener.onSuccess(response);
                    } else {
                        ResponseException responseException = new ResponseException(response);
                        responseListener.onFailure(responseException);
                    }
                }

                public void failed(Exception e) {
                    responseListener.onFailure(e);
                }

                public void cancelled() {
                    responseListener.onFailure(new ExecutionException("Request was cancelled", null));
                }
            });
        } catch (Exception e) {
            responseListener.onFailure(e);
        }
    }

    public void close() throws IOException {
        this.client.close();
    }

    private HttpRequestBase createHttpRequest(Request request) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(request.getEndpoint())
            .addParameter(APPID_PARAMETER_NAME, this.appId);
        for (Map.Entry<String, String> entry : request.getParameters().entrySet()) {
            uriBuilder.addParameter(entry.getKey(), entry.getValue());
        }

        URI uri = uriBuilder.build();

        HttpRequestBase httpRequest;
        RequestMethod requestMethod = request.getRequestMethod();
        switch (requestMethod) {
            case GET:
                httpRequest = new HttpGet(uri);
                break;
            case POST:
                HttpPost httpPostRequest = new HttpPost(uri);
                httpPostRequest.addHeader(CONTENT_TYPE_HEADER_NAME, APPLICATION_JSON_CONTENT_TYPE);
                httpPostRequest.setEntity(request.getEntity());
                httpRequest = httpPostRequest;
                break;
            default:
                throw new UnsupportedOperationException("Http method not supported: " + requestMethod.toString());
        }

        if (this.subscriptionId != null && this.subscriptionId.trim().length() > 0) {
            httpRequest.addHeader(SUBSCRIPTIONID_HEADER_NAME, this.subscriptionId);
        }

        for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
            httpRequest.addHeader(entry.getKey(), entry.getValue());
        }

        return httpRequest;
    }
}
