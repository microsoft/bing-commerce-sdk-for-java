package com.microsoft.bing.retailsearch.common.responses;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class Response {
    private static final ObjectMapper MAPPER  = new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private final HttpResponse response;

    public Response(HttpResponse response) {
        this.response = response;
    }

    public int getStatusCode() { return this.response.getStatusLine().getStatusCode(); }

    public Header[] getHeaders() { return this.response.getAllHeaders(); }

    public String getHeader(String name) {
        Header header = this.response.getFirstHeader(name);
        if (header == null) {
            return null;
        }
        return header.getValue();
    }

    public HttpEntity getEntity() { return this.response.getEntity(); }

    public HttpResponse getHttpResponse() { return this.response; }

    public boolean isErrorResponse() {
        int statusCode = this.getStatusCode();
        if (statusCode < 200 || statusCode > 299) {
            return true;
        }

        return false;
    }

    public <T extends ApiResponseBase> T mapToApiResponse(Class<T> classType) throws IOException {
        String responseBody = EntityUtils.toString(this.response.getEntity());
        T apiResponse = MAPPER.readValue(responseBody, classType);
        apiResponse.setInternalResponse(this);
        return apiResponse;
    }
}
