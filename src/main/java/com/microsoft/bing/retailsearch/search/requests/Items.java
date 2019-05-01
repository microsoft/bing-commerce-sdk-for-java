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

    public void setSelect(List<String> select) {
        this.select = select;
    }

    public String getOrderBy() {
        return this.orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public short getTop() {
        return this.top;
    }

    public void setTop(short top) {
        this.top = top;
    }

    public int getSkip() {
        return this.skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public boolean getDedupe() {
        return this.dedupe;
    }

    public void setDedupe(boolean dedupe) {
        this.dedupe = dedupe;
    }
}
