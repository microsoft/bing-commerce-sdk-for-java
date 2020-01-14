package com.microsoft.bing.commerce.ingestion.util;

public interface AccessTokenProvider {
    public String getAccessToken();
    public String refreshAccessToken();
}
