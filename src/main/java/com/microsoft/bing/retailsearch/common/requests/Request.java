package com.microsoft.bing.retailsearch.common.requests;

import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;

public final class Request {

    private final RequestMethod requestMethod;
    private final String endpoint;

    private Map<String, String> parameters = new HashMap<String, String>();
    private Map<String, String> headers = new HashMap<String, String>();
    private HttpEntity entity;

    public Request(RequestMethod requestMethod, String endpoint) {
        this.requestMethod = requestMethod;
        this.endpoint = endpoint;
    }

    public RequestMethod getRequestMethod() {
        return this.requestMethod;
    }

    public String getEndpoint() {
        return this.endpoint;
    }

    public Map<String, String> getParameters() {
        return this.parameters;
    }

    public void addParameter(String name, String value) {
        this.parameters.put(name, value);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void addHeader(String name, String value) {
        this.headers.put(name, value);
    }

    public HttpEntity getEntity() {
        return entity;
    }

    public void setEntity(HttpEntity entity) {
        this.entity = entity;
    }

    public enum RequestMethod {
        GET,
        POST
    }
}
