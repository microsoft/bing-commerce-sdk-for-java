package com.microsoft.bing.retailsearch.common.responses;

import java.util.List;

public class ErrorResponse extends ApiResponseBase {

    private List<Error> errors;

    public List<Error> getErrors() {
        return this.errors;
    }
}
