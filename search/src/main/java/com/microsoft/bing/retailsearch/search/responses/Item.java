package com.microsoft.bing.retailsearch.search.responses;

import java.util.List;

public class Item {
    private String indexId;
    private String itemId;
    private double score;
    private List<FieldBase> fields;

    public String getIndexId() { return this.indexId; }

    public String getItemId() { return this.itemId; }

    public double getScore() { return this.score; }

    public List<FieldBase> getFields() { return this.fields; }
}
