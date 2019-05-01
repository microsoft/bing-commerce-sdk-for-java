package com.microsoft.bing.retailsearch.common.responses;

public class Error {

    private String code;
    private String subCode;
    private String message;
    private String moreDetails;
    private String parameter;

    public String getCode() {
        return this.code;
    }

    public String getSubCode() {
        return this.subCode;
    }

    public String getMessage() {
        return this.message;
    }

    public String getMoreDetails() {
        return this.moreDetails;
    }

    public String getParameter() {
        return this.parameter;
    }
}
