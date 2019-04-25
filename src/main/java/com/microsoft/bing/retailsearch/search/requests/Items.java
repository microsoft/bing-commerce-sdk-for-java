package com.microsoft.bing.retailsearch.search.requests;

import java.util.List;

public final class Items {
    private List<String> select;
    private String orderBy;
    private short top = 24;
    private int skip = 0;
    private boolean dedupe = false;

    public List<String> getSelect() {
        return this.select;
    }

    public Items setSelect(List<String> select) {
        this.select = select;
        return this;
    }

    public String getOrderBy() {
        return this.orderBy;
    }

    public Items setOrderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public short getTop() {
        return this.top;
    }

    public Items setTop(short top) {
        this.top = top;
        return this;
    }

    public int getSkip() {
        return this.skip;
    }

    public Items setSkip(int skip) {
        this.skip = skip;
        return this;
    }

    public boolean getDedupe() {
        return this.dedupe;
    }

    public Items setDedupe(boolean dedupe) {
        this.dedupe = dedupe;
        return this;
    }
}
