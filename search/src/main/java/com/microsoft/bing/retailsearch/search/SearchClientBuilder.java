package com.microsoft.bing.retailsearch.search;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

public final class SearchClientBuilder {
    private static final int DEFAULT_CONNECTION_TIMEOUT_MS = 1000;
    private static final int DEFAULT_SOCKET_TIMEOUT_MS = 5000;
    
    private String appId;
    private String subscriptionId;
    private String tenantId;

    public SearchClientBuilder setDefaultAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public SearchClientBuilder setDefaultSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public SearchClientBuilder setDefaultTenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public SearchClient build() {
        RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT_MS)
            .setSocketTimeout(DEFAULT_SOCKET_TIMEOUT_MS).build();

        CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
            .setDefaultRequestConfig(requestConfig)
            .build();

        httpclient.start();

        SearchClient searchClient = new SearchClient(
            httpclient,
            this.appId,
            this.subscriptionId,
            this.tenantId);
        return searchClient;
    }
}
