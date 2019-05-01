package com.microsoft.bing.retailsearch.search.responses;

import java.util.List;

public class Pagination {
    private Integer currentPageNumber;
    private Page previousPage;
    private Page nextPage;
    private List<Page> pages;

    public Integer getCurrentPageNumber() { return this.currentPageNumber; }

    public Page getPreviousPage() { return this.previousPage; }

    public Page getNextPage() { return this.nextPage; }

    public List<Page> getPages() { return this.pages; }
}
