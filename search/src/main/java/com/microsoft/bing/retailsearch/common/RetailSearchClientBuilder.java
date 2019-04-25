package com.microsoft.bing.retailsearch.common;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

public final class RetailSearchClientBuilder {
    private static final int DEFAULT_CONNECTION_TIMEOUT_MS = 1000;
    private static final int DEFAULT_SOCKET_TIMEOUT_MS = 5000;

    private String appId;
    private String subscriptionId;

    public RetailSearchClientBuilder setDefaultAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public RetailSearchClientBuilder setDefaultSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public RetailSearchClient build() {
        RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT_MS)
            .setSocketTimeout(DEFAULT_SOCKET_TIMEOUT_MS).build();

        CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
            .setDefaultRequestConfig(requestConfig)
            .build();

        httpclient.start();

        RetailSearchClient retailSearchClient = new RetailSearchClient(
            httpclient,
            this.appId,
            this.subscriptionId);
        return retailSearchClient;
    }
}
