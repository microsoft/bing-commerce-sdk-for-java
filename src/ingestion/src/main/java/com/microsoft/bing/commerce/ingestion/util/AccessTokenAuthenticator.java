package com.microsoft.bing.commerce.ingestion.util;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class AccessTokenAuthenticator implements Authenticator {

    private final AccessTokenProvider accessTokenProvider;

    public AccessTokenAuthenticator(AccessTokenProvider accessTokenProvider) {
        this.accessTokenProvider = accessTokenProvider;
    }

    @Override
    public Request authenticate(Route route, Response response) {
        final String accessToken = accessTokenProvider.getAccessToken();
        if (!isRequestWithAccessToken(response) || accessToken == null) {
            return null;
        }

        synchronized (this) {
            final String newAccessToken = accessTokenProvider.getAccessToken();
            // Access token is refreshed in another thread.
            if (!accessToken.equals(newAccessToken)) {
                return newRequestWithAccessToken(response.request(), newAccessToken);
            }

            // Need to refresh an access token
            final String updatedAccessToken = accessTokenProvider.refreshAccessToken();
            return newRequestWithAccessToken(response.request(), updatedAccessToken);
        }
    }

    private boolean isRequestWithAccessToken(final Response response) {
        String header = response.request().header("Authorization");
        return header != null && header.startsWith("Bearer");
    }

    private Request newRequestWithAccessToken(Request request, final String accessToken) {
        return request.newBuilder()
                .header("Authorization", "Bearer " + accessToken)
                .build();
    }
}