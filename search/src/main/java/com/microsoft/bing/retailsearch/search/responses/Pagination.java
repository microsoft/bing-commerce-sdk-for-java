package com.microsoft.bing.retailsearch.search.responses;

import java.util.List;

public class Pagination {
    private int currentPageNumber;
    private Page previousPage;
    private Page nextPage;
    private List<Page> pages;

    public int getCurrentPageNumber() { return this.currentPageNumber; }

    public Page getPreviousPage() { return this.previousPage; }

    public Page getNextPage() { return this.nextPage; }

    public List<Page> getPages() { return this.pages; }
}
