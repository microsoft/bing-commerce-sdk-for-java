package com.microsoft.bing.retailsearch.search.responses;

public class Page {
    private int pageNumber;
    private int skip;
    private short top;

    public int getPageNumber() { return this.pageNumber; }

    public int getSkip() { return this.skip; }

    public short getTop() { return this.top; }
}
