package com.microsoft.bing.retailsearch.search.responses;

public class Page {

    private Integer pageNumber;
    private Integer skip;
    private Short top;

    public Integer getPageNumber() {
        return this.pageNumber;
    }

    public Integer getSkip() {
        return this.skip;
    }

    public Short getTop() {
        return this.top;
    }
}
