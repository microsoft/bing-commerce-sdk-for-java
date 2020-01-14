/**
 * 
 */
package com.microsoft.bing.commerce.ingestion.util;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AccessTokenInterceptor implements Interceptor {

	private final AccessTokenProvider accessTokenProvider;

	public AccessTokenInterceptor(AccessTokenProvider accessTokenProvider) {
		this.accessTokenProvider = accessTokenProvider;
	}

	@Override
	public Response intercept(Chain chain) throws IOException {

		String accessToken = accessTokenProvider.getAccessToken();
		Request request = newRequestWithAccessToken(chain.request(), accessToken);
		return chain.proceed(request);
	}

	private Request newRequestWithAccessToken(Request request, final String accessToken) {
		return request.newBuilder()
				.header("Authorization", "Bearer " + accessToken)
				.build();
	}
}
