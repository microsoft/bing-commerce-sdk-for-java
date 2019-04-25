package com.microsoft.bing.retailsearch.common;

public interface ResponseListener<T> {
    void onSuccess(T response);

    void onFailure(Exception e);
}
