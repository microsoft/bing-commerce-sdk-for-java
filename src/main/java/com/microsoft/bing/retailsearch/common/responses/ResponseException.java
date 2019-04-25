package com.microsoft.bing.retailsearch.common.responses;

import java.io.IOException;
import java.util.List;

public class ResponseException extends IOException {
    private final Response response;
    private ErrorResponse errorResponse;

    public ResponseException(Response response) {
        super("An error occurred in the request.");
        this.response = response;

        try {
            this.errorResponse = response.mapToApiResponse(ErrorResponse.class);
        } catch (IOException e) {
            // Not able to parse response body
        }
    }

    public Response getResponse() { return this.response; }

    public List<Error> getErrors() {
        if (this.errorResponse != null) {
            return this.errorResponse.getErrors();
        }

        return null;
    }
}
