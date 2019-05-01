package com.microsoft.bing.retailsearch.search.responses;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "_type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = BooleanField.class, name = "BoolField"),
    @JsonSubTypes.Type(value = NumericField.class, name = "NumericField"),
    @JsonSubTypes.Type(value = StringField.class, name = "StringField") }
)
public abstract class FieldBase {
    private String name;

    public String getName() { return this.name; }
}
