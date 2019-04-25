package com.microsoft.bing.retailsearch.common.responses;

public abstract class ApiResponseBase {
    private Response internalResponse;

    public Response getInternalResponse() { return this.internalResponse; }

    void setInternalResponse(Response response) {
        this.internalResponse = response;
    }
}
